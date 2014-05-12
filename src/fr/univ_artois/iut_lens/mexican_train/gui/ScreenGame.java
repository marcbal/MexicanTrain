package fr.univ_artois.iut_lens.mexican_train.gui;

import org.jsfml.graphics.*;
import org.jsfml.system.*;
import org.jsfml.window.Keyboard.Key;
import org.jsfml.window.event.Event;

import fr.univ_artois.iut_lens.mexican_train.game.*;

public class ScreenGame extends Screen {
	
	private Game _game;
	private WDomino[][] _wDominosTrains;
	private Text[] _textTrains;
	private WButton[] _wButtonsTrains;
	private ButtonValue _buttonsTrainsValue = new ButtonValue(-1);
	private Text _textReserve = new Text();
	private Text _textPioche = new Text();
	private WDomino[][] _wDominosReserve;
	private WDomino _wDominoDepart;
	private GameSettings _gameSettings;
	
	private Text _helpInvite = new Text();
	private Text _helpText = new Text();
	private RectangleShape _helpBackground = new RectangleShape();
	private Text _finishText = new Text();
	private RectangleShape _finishBackground = new RectangleShape();
	private boolean _helpDisplay = false;

	private WButton _boutonRetour;
	private WButton _boutonPasser;
	private WButton _boutonPiocher;
	private WButton _boutonRejouer;
	private ButtonValue _boutonAction = new ButtonValue(-1);
	
	
	
	
	
	
	public ScreenGame(Vector2i window_size, ButtonValue ecran_actif, GameSettings gameSettings) {
		super(window_size);
		
		_gameSettings = gameSettings;
		
		_textReserve.setFont(Widget.getThemeFont());
		_textReserve.setCharacterSize(15);
		_textReserve.setColor(Widget._theme_txt_color);
		
		_textPioche.setFont(Widget.getThemeFont());
		_textPioche.setCharacterSize(15);
		_textPioche.setColor(Widget._theme_txt_color);

		_helpInvite.setFont(Widget.getThemeFont());
		_helpInvite.setCharacterSize(15);
		_helpInvite.setColor(Widget._theme_txt_color);
		_helpInvite.setString("Touche H pour\nafficher l'aide");
		
		_helpText.setFont(Widget.getThemeFont());
		_helpText.setCharacterSize(15);
		_helpText.setColor(Widget._theme_txt_color);
		_helpText.setString("Commandes pour jouer (à la souris) :\n"+
				"Clique droite sur un domino de la réserve pour inverser les valeurs.\n"+
				"Clique gauche sur un domino de la réserve pour le selectionner. Ensuite :\n"+
				"   - Clique sur le bouton \"Poser\" d'un train pour y placer ton domino\n"+
				"ou - Clique sur un autre emplacement de la réserve pour échanger\n"+
				"\n"+
				"Touches du clavier :\n"+
				"F3 pour afficher ou masquer des informations techniques.\n"+
				"F4 pour afficher ou masquer le fond d'écran du jeu.\n"+
				"F5 pour (dés)activer l'animation du fond d'écran du jeu.\n"+
				"H pour afficher ou masquer ce texte d'aide.");
		_helpBackground.setFillColor(Color.WHITE);
		_helpBackground.setOutlineColor(Color.BLACK);
		_helpBackground.setOutlineThickness(2);
		

		
		Vector2f backgroundSize = new Vector2f(_helpText.getGlobalBounds().width+20, _helpText.getGlobalBounds().height+20);
		_helpBackground.setSize(backgroundSize);
		_helpBackground.setPosition(Math.round((window_size.x - backgroundSize.x)/2.0f),
				Math.round((window_size.y - backgroundSize.y)/2.0f));
		_helpText.setPosition(Vector2f.add(_helpBackground.getPosition(), new Vector2f(5, 5)));
		
		_finishText.setFont(Widget.getThemeFont());
		_finishText.setCharacterSize(15);
		_finishText.setColor(Widget._theme_txt_color);
		_finishBackground.setFillColor(Color.WHITE);
		_finishBackground.setOutlineColor(Color.BLACK);
		_finishBackground.setOutlineThickness(2);
		
		_boutonRetour = new WButton(Vector2f.sub(new Vector2f(window_size), new Vector2f(105, 62)), new Vector2f(80, 25), true, ecran_actif, Application.SCR_INDEX);
		_boutonRetour.setText("Menu");
		_boutonRejouer = new WButton(Vector2f.sub(new Vector2f(window_size), new Vector2f(105, 35)), new Vector2f(80, 25), true, _boutonAction, 2);
		_boutonRejouer.setText("Rejouer");
		_boutonPiocher = new WButton(Vector2f.sub(new Vector2f(window_size), new Vector2f(269, 62)), new Vector2f(162, 52), true, _boutonAction, 0);
		_boutonPiocher.setText("Piocher");
		_boutonPasser = new WButton(Vector2f.sub(new Vector2f(window_size), new Vector2f(269, 62)), new Vector2f(162, 52), true, _boutonAction, 1);
		_boutonPasser.setText("Passer");
		
		
		restartGame();
		
		
		
		
	}

	

	
	
	
	
	
	
	
	
	
	
	
	
	
	public void update(Time elapsed_time)
	{
		
		if (!_game.getActivPlayer().isComputer())
		{
			if (_boutonAction.val == 0 && !_game.hasActivPlayerPioche())
				_game.activPlayerPioche();
			else if (_boutonAction.val == 1 && _game.hasActivPlayerPioche())
			{
				_game.nextPlayer();
				unclickDominosReserve();
			}
			else if (_buttonsTrainsValue.val == 0 && indexDominoReserveClicked() >= 0)
			{
				if (_game.getActivPlayer().addDominoMexicanTrain(_game.getMexicanTrain(), indexDominoReserveClicked()))
				{
					_game.nextPlayer();
					unclickDominosReserve();
				}
			}
			else if (_buttonsTrainsValue.val == _game.getActivPlayerIndex()+1 && indexDominoReserveClicked() >= 0)
			{
				if (_game.getActivPlayer().addDominoOwnTrain(indexDominoReserveClicked()))
				{
					_game.nextPlayer();
					unclickDominosReserve();
				}
			}
			else if (_buttonsTrainsValue.val > 0 && indexDominoReserveClicked() >= 0)
			{
				if (_game.getActivPlayer().addDominoOtherTrain(_game.getPlayer(_buttonsTrainsValue.val-1), indexDominoReserveClicked()))
				{
					_game.nextPlayer();
					unclickDominosReserve();
				}
			}
		}
		

		if (_boutonAction.val == 2)
		{
			restartGame();
		}
		
		
		// vérifie si des dominos sont à échanger dans la réserve du joueur affiché
		swapDominoReserve();
		
		
		// faire jouer les ordinateurs
		_game.playComputer();
		
		
		
		Domino[] dominosReserve = _game.getActivPlayer().getReserve().getDominoes();
		
		_textReserve.setString("Réserve de "+_game.getActivPlayer().getName()+ " :");
		_textPioche.setString("Pioche : "+((_game.getPioche().nbDomino() > 0) ? _game.getPioche().nbDomino()+" domino"+((_game.getPioche().nbDomino() > 1) ? "s":"") : "vide"));
		_textPioche.setColor(new Color((int) (255 * Math.exp(-_game.getPioche().nbDomino()/4.0)), 0, 0));
	
		// -- mise à jour des dominos de la réserve --
		int i=0;
		for (int j=0; j<_wDominosReserve.length; j++)
			for (int k=0; k<_wDominosReserve[j].length; k++)
			{
				if (i < dominosReserve.length && !_game.getActivPlayer().isComputer())
				{
					_wDominosReserve[j][k].setDomino(dominosReserve[i]);
				}
				else
					_wDominosReserve[j][k].setDomino(null);
				i++;
			}
		// -------------------------------------------
		
		
		// ------ mise à jour du train mexicain ------
		Train tm = _game.getMexicanTrain();
		Domino[] domTm = tm.getDominos();
		// i est le premier domino affiché
		i = 0;
		if (tm.nbDomino() > _wDominosTrains[0].length)
			i = tm.nbDomino() - _wDominosTrains[0].length;
		
		for (int j=0; j<_wDominosTrains[0].length; j++)
		{
			if (i < tm.nbDomino())
			{
				_wDominosTrains[0][j].setDomino(domTm[i]);
			}
			else
				_wDominosTrains[0][j].setDomino(null);
			i++;
		}
		// -------------------------------------------
		
		
		
		// --- mise à jour des trains des joueurs ----
		Player[] players = _game.getPlayers();
		for (int j=0; j<players.length; j++)
		{
			Player p = players[j];
			// texte au dessus du train de chaque joueurs
			_textTrains[j+1].setString(p.getName()+" ("+
						((p.getReserve().nbDominos()>0) ?
								"réserve de "+p.getReserve().nbDominos()+" domino"+((p.getReserve().nbDominos()>1)?"s":"") :
								"réserve vide : il gagne ! ")
						+") :");
			if (p.getReserve().nbDominos() == 0)
				_textTrains[j+1].setColor(new Color(0, 192, 0));
			else if (p == _game.getActivPlayer())
				_textTrains[j+1].setColor(Color.RED);
			else
				_textTrains[j+1].setColor(Widget._theme_txt_color);
			Train t =  players[j].getTrain();
			Domino[] doms = t.getDominos();
			i=0;
			if (t.nbDomino() > _wDominosTrains[j+1].length)
				i = t.nbDomino() - _wDominosTrains[j+1].length;
			
			for (int k=0; k<_wDominosTrains[j+1].length; k++)
			{
				if (i < t.nbDomino())
				{
					_wDominosTrains[j+1][k].setDomino(doms[i]);
				}
				else
					_wDominosTrains[j+1][k].setDomino(null);
				i++;
			}
			
			_wButtonsTrains[j+1].setEnabled(
					((p == _game.getActivPlayer() || p.getTrain().canOtherPlayerAddDomino()) && !_game.isFinished()
							&& indexDominoReserveClicked() >= 0)
					);
				
		}
		_wButtonsTrains[0].setEnabled(!_game.isFinished() && indexDominoReserveClicked() >= 0);
		// -------------------------------------------
		
		
		
		if (_game.isFinished())
		{
			String finishString = "La partie est terminée !\n"+
					"Voici les scores :";
			
			for (int j = 0; j<players.length; j++)
			{
				int sommePoint = 0;
				Domino[] doms = players[j].getReserve().getDominoes();
				for (int k=0; k<doms.length; k++)
					if (doms[k] != null)
						sommePoint += doms[k].getVal();
				
				finishString += "\n"+players[j].getName()+" : "+
						players[j].getReserve().nbDominos()+" domino"+((players[j].getReserve().nbDominos()>1)?"s":"")+", "+
						sommePoint+" point"+((sommePoint>1)?"s":"");
			}
			
			finishString += "\nLe joueur n'ayant plus aucun domino dans sa réserve a gagné.\n"+
					"Si aucun joueur n'a réussi à poser tout ses dominos, le\n"+
					"joueur ayant le moins de points gagne la partie.\n"+
					"Cliquez sur Rejouer pour commencer une nouvelle partie.";
			
			_finishText.setString(finishString);
			Vector2f backgroundSize = new Vector2f(_finishText.getGlobalBounds().width+20, _finishText.getGlobalBounds().height+20);
			_finishBackground.setSize(backgroundSize);
			_finishBackground.setPosition(Math.round((_window_size.x - backgroundSize.x)/2.0f),
					Math.round((_window_size.y - backgroundSize.y)/2.0f));
			_finishText.setPosition(Vector2f.add(_finishBackground.getPosition(), new Vector2f(5, 5)));
		}
		
		
		
		
		_boutonAction.val = -1;
		_buttonsTrainsValue.val = -1;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public void onEvent(Event event)
	{
		if (event.type == Event.Type.KEY_PRESSED && event.asKeyEvent().key == Key.H)
        	_helpDisplay = !_helpDisplay;
		
		// ce qui précède concerne les évènements concernant l'aide
		if (_helpDisplay)
			return;
		// la suite concerne les évènements du jeu (domino, boutons)
		
		for (int i = 0; i<_wDominosTrains.length; i++)
			for (int j = 0; j<_wDominosTrains[i].length; j++)
				_wDominosTrains[i][j].onEvent(event);

		for (int i = 0; i<_wDominosReserve.length; i++)
			for (int j = 0; j<_wDominosReserve[i].length; j++)
				_wDominosReserve[i][j].onEvent(event);
		
		_boutonRetour.onEvent(event);
		_boutonRejouer.onEvent(event);
		

		for (int i = 0; i<_wButtonsTrains.length; i++)
			if (_wButtonsTrains[i].isEnabled())
				_wButtonsTrains[i].onEvent(event);
		
		if (_game.hasActivPlayerPioche())
			_boutonPasser.onEvent(event);
		else
			_boutonPiocher.onEvent(event);
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public void restartGame()
	{
		_game = new Game(_gameSettings);
		_wDominosTrains = new WDomino[_gameSettings.getNbPlayers() + 1][8];
		_wDominosReserve = new WDomino[10][3];
		_textTrains = new Text[_wDominosTrains.length];
		_wButtonsTrains = new WButton[_wDominosTrains.length];

		
		
		// quelques propriétés de dimensions des éléments
		float _screenMargin = 10;
		Vector2f wDominoSize = new Vector2f(80, 40);
		Vector2f trainFieldSize = new Vector2f(650, _window_size.y-2*_screenMargin);
		float trainFieldPadding = 0;
		float TextHeight = 18;
		
		
		// on positionne les éléments
		float separationHeight = (trainFieldSize.y - 2 * trainFieldPadding - _wDominosTrains.length * (wDominoSize.y + TextHeight)) / (float) (_wDominosTrains.length-1);
		float separationWidth = (trainFieldSize.x - 2 * trainFieldPadding - _wDominosTrains[0].length * wDominoSize.x) / (float) (_wDominosTrains[0].length-1);
		for (int i = 0; i<_wDominosTrains.length; i++)
		{
			float yText = _screenMargin + trainFieldPadding + i * (TextHeight + wDominoSize.y + separationHeight);
			float xText = _screenMargin + trainFieldPadding;
			_textTrains[i] = new Text();
			_textTrains[i].setFont(Widget.getThemeFont());
			_textTrains[i].setCharacterSize(15);
			_textTrains[i].setColor(Widget._theme_txt_color);
			_textTrains[i].setPosition(xText, yText);
			if (i==0)
				_textTrains[i].setString("Train mexicain :");
			
			
			float yDomino = yText + TextHeight;
			

			
			for (int j = 0; j<_wDominosTrains[i].length; j++)
			{
				float xDomino = xText + j * (wDominoSize.x + separationWidth);
				_wDominosTrains[i][j] = new WDomino(new Vector2f(xDomino, yDomino), wDominoSize, true, null, false, false);
				
			}
			
			_wButtonsTrains[i] = new WButton(new Vector2f(xText + _wDominosTrains[i].length * (wDominoSize.x + separationWidth), yDomino),
					wDominoSize, false, _buttonsTrainsValue, i);
			_wButtonsTrains[i].setText("Poser");
		}
		
		Vector2f reserveFieldSize = new Vector2f(_window_size.x - trainFieldSize.x - 80 - 4*_screenMargin, _window_size.y-2*_screenMargin-wDominoSize.y-TextHeight-70);
		float reserveFieldPadding = 5;
		
		_textReserve.setPosition(_window_size.x - _screenMargin - reserveFieldPadding - reserveFieldSize.x,
				_screenMargin + wDominoSize.y);
		_helpInvite.setPosition(_window_size.x - _screenMargin - reserveFieldPadding - reserveFieldSize.x,
				_screenMargin);
		
		// on positionne les éléments
		separationHeight = (reserveFieldSize.y - 2 * reserveFieldPadding - _wDominosReserve.length * wDominoSize.y) / (float) (_wDominosReserve.length-1);
		separationWidth = (reserveFieldSize.x - 2 * reserveFieldPadding - _wDominosReserve[0].length * wDominoSize.x) / (float) (_wDominosReserve[0].length-1);
		for (int i = 0; i<_wDominosReserve.length; i++)
		{
			float yDomino = _screenMargin + reserveFieldPadding + TextHeight + wDominoSize.y + i * (wDominoSize.y + separationHeight);
			float xDomino0 = _textReserve.getPosition().x;
			for (int j = 0; j<_wDominosReserve[i].length; j++)
			{
				float xDomino = xDomino0 + j * (wDominoSize.x + separationWidth);
				_wDominosReserve[i][j] = new WDomino(new Vector2f(xDomino, yDomino), wDominoSize, true, null, false, true);
				
			}
		}
		_textPioche.setPosition(_textReserve.getPosition().x,
				_screenMargin + reserveFieldPadding + TextHeight + wDominoSize.y + _wDominosReserve.length * (wDominoSize.y + separationHeight));

		unclickDominosReserve();
		
		_wDominoDepart = new WDomino(new Vector2f(_window_size.x-_screenMargin-wDominoSize.x, _screenMargin), wDominoSize, false, _game.getDominoDepart(), false, false);
	}
	
	
	
	

	
	private int indexDominoReserveClicked()
	{

		for (int i = 0; i<_wDominosReserve.length; i++)
			for (int j = 0; j<_wDominosReserve[i].length; j++)
				if(_wDominosReserve[i][j].isClicked() && _wDominosReserve[i][j].getDomino() != null)
					return i * _wDominosReserve[i].length + j;
		return -1;
	}
	
	private int nbDominoReserveClicked()
	{
		int c = 0;
		for (int i = 0; i<_wDominosReserve.length; i++)
			for (int j = 0; j<_wDominosReserve[i].length; j++)
				if(_wDominosReserve[i][j].isClicked())
					c++; // lol
		return c;
	}

	private void swapDominoReserve()
	{
		if (nbDominoReserveClicked() != 2)
			return;
		int i1 = -1, i2 = -1; 
		for (int i = 0; i<_wDominosReserve.length; i++)
			for (int j = 0; j<_wDominosReserve[i].length; j++)
			{
				if(_wDominosReserve[i][j].isClicked() && i1 == -1)
					i1 = i * _wDominosReserve[i].length + j;
				else if (_wDominosReserve[i][j].isClicked())
					i2 = i * _wDominosReserve[i].length + j;
			}
		_game.getActivPlayer().getReserve().swap(i1, i2);
		unclickDominosReserve();
	}
	
	
	
	private void unclickDominosReserve()
	{
		for (int i = 0; i<_wDominosReserve.length; i++)
			for (int j = 0; j<_wDominosReserve[i].length; j++)
				_wDominosReserve[i][j].unclick();
	}
	
	
	
	
	
	
	public void draw(RenderTarget target, RenderStates states)
	{
		for (int i = 0; i<_wDominosTrains.length; i++)
			for (int j = 0; j<_wDominosTrains[i].length; j++)
				target.draw(_wDominosTrains[i][j], states);

		for (int i = 0; i<_wDominosReserve.length; i++)
			for (int j = 0; j<_wDominosReserve[i].length; j++)
				target.draw(_wDominosReserve[i][j], states);
		

		for (int i = 0; i<_textTrains.length; i++)
			target.draw(_textTrains[i], states);

		for (int i = 0; i<_wButtonsTrains.length; i++)
			if (_wButtonsTrains[i].isEnabled())
				target.draw(_wButtonsTrains[i], states);
		
		target.draw(_textReserve, states);
		target.draw(_textPioche, states);
		
		target.draw(_wDominoDepart, states);
		
		target.draw(_boutonRetour, states);
		target.draw(_boutonRejouer, states);
		
		if (_game.hasActivPlayerPioche() && !_game.isFinished())
			target.draw(_boutonPasser, states);
		else if (!_game.getActivPlayer().isComputer() && !_game.isFinished())
			target.draw(_boutonPiocher, states);
		

		target.draw(_helpInvite, states);
		
		if (_game.isFinished())
		{
			target.draw(_finishBackground, states);
			target.draw(_finishText, states);
		}
		
		
		if (_helpDisplay)
		{
			target.draw(_helpBackground, states);
			target.draw(_helpText, states);
		}
	}
	
}
