package fr.univ_artois.iut_lens.mexican_train.gui;

import java.awt.Font;

import org.jsfml.graphics.Color;
import org.jsfml.graphics.RenderStates;
import org.jsfml.graphics.RenderTarget;
import org.jsfml.graphics.Text;
import org.jsfml.system.*;
import org.jsfml.window.event.Event;

import fr.univ_artois.iut_lens.mexican_train.game.GameSettings;

public class ScreenPlayerChoice extends Screen {
	
	private GameSettings _gameSettings;
	
	
	private WButton[] _boutonsNbPlayer = new WButton[GameSettings.maxPlayers];
	private ButtonValue _valueBoutonNbPlayer;
	
	private WButton[] _boutonsNbHuman = new WButton[GameSettings.maxPlayers];
	private ButtonValue _valueBoutonNbHuman;
	
	private WButton[] _FieldPlayerName = new WButton[GameSettings.maxPlayers];
	
	private WButton _boutonJouer;
	private WButton _boutonRetour;
	
	private Text _titre = new Text();
	private Text _avertissement = new Text();
	private Text _infosSettings = new Text();
	private Text[] _headers = new Text[3];

	
	
	
	
	
	public ScreenPlayerChoice(Vector2i window_size, ButtonValue ecran_actif, GameSettings gameSettings) {
		super(window_size);
		_gameSettings = gameSettings;
		_valueBoutonNbPlayer = new ButtonValue(_gameSettings.getNbPlayers());
		_valueBoutonNbHuman = new ButtonValue(_gameSettings.getNbHumans());
		
		_titre.setFont(Widget.getThemeFont());
		_titre.setCharacterSize(25);
		_titre.setColor(Color.BLACK);
		_titre.setString("Paramètres de jeu");

		// on centre le texte
		_titre.setOrigin(0, 0);
		_titre.setPosition(0, 0);
		_titre.setOrigin(_titre.getGlobalBounds().left + _titre.getGlobalBounds().width/2.0f,
				_titre.getGlobalBounds().top + _titre.getGlobalBounds().height/2.0f);
		_titre.setPosition(window_size.x/2.0f,
						  50/2.0f);
		
		float windowPadding = 10;
		Vector2f boutonNbPlayerSize = new Vector2f(125, 42);
		Vector2f boutonNbHumanSize = boutonNbPlayerSize;
		Vector2f fieldPlayerNameSize = new Vector2f(350, boutonNbPlayerSize.y);
		
		Vector2f boutonAreaSize = Vector2f.sub(new Vector2f(_window_size), new Vector2f(2*windowPadding, 2*windowPadding+200));
		Vector2f boutonAreaPos = new Vector2f(windowPadding, windowPadding+150);

		float horizSpace = (boutonAreaSize.x - boutonNbPlayerSize.x - boutonNbHumanSize.x - fieldPlayerNameSize.x) / 3.0f;
		float vertSpace = (boutonAreaSize.y - GameSettings.maxPlayers * boutonNbPlayerSize.y) / (float) GameSettings.maxPlayers;
		
		_infosSettings = new Text();
		_infosSettings.setFont(Widget.getThemeFont());
		_infosSettings.setCharacterSize(15);
		_infosSettings.setColor(Color.BLACK);
		_infosSettings.setPosition(windowPadding, 50);
		

		_avertissement = new Text();
		_avertissement.setFont(Widget.getThemeFont());
		_avertissement.setStyle(Font.BOLD);
		_avertissement.setCharacterSize(15);
		_avertissement.setColor(Color.BLACK);
		_avertissement.setPosition(400, _window_size.y - windowPadding - 40);
		_avertissement.setString("Cliquez sur \"Rejouer\" sur l'écran de jeu\npour appliquer les modifications.");
		
		_headers[0] = new Text();
		_headers[0].setFont(Widget.getThemeFont());
		_headers[0].setCharacterSize(15);
		_headers[0].setColor(Color.BLACK);
		_headers[0].setString("Nombre de joueurs");

		_headers[0].setOrigin(0, 0);
		_headers[0].setPosition(0, 0);
		_headers[0].setOrigin(_headers[0].getGlobalBounds().left + _headers[0].getGlobalBounds().width/2.0f,
				_headers[0].getGlobalBounds().top + _headers[0].getGlobalBounds().height/2.0f);
		_headers[0].setPosition(boutonAreaPos.x + horizSpace / 2.0f + boutonNbPlayerSize.x / 2.0f,
				boutonAreaPos.y + vertSpace / 2.0f - (boutonNbPlayerSize.y + vertSpace) - 15);

		_headers[1] = new Text();
		_headers[1].setFont(Widget.getThemeFont());
		_headers[1].setCharacterSize(15);
		_headers[1].setColor(Color.BLACK);
		_headers[1].setString("Nombre de joueurs humain");

		_headers[1].setOrigin(0, 0);
		_headers[1].setPosition(0, 0);
		_headers[1].setOrigin(_headers[1].getGlobalBounds().left + _headers[1].getGlobalBounds().width/2.0f,
				_headers[1].getGlobalBounds().top + _headers[1].getGlobalBounds().height/2.0f);
		_headers[1].setPosition(boutonAreaPos.x + 3*horizSpace / 2.0f + boutonNbPlayerSize.x + boutonNbHumanSize.x / 2.0f,
				boutonAreaPos.y + vertSpace / 2.0f - (boutonNbPlayerSize.y + vertSpace) - 15);

		_headers[2] = new Text();
		_headers[2].setFont(Widget.getThemeFont());
		_headers[2].setCharacterSize(15);
		_headers[2].setColor(Color.BLACK);
		_headers[2].setString("Nom des joueurs");

		_headers[2].setOrigin(0, 0);
		_headers[2].setPosition(0, 0);
		_headers[2].setOrigin(_headers[2].getGlobalBounds().left + _headers[2].getGlobalBounds().width/2.0f,
				_headers[2].getGlobalBounds().top + _headers[2].getGlobalBounds().height/2.0f);
		_headers[2].setPosition(boutonAreaPos.x + 5*horizSpace / 2.0f + boutonNbPlayerSize.x + boutonNbHumanSize.x + fieldPlayerNameSize.x / 2.0f,
				boutonAreaPos.y + vertSpace / 2.0f - (boutonNbPlayerSize.y + vertSpace) - 15);
		
		
		
		
		
		for (int i=0; i<GameSettings.maxPlayers; i++)
		{
			float yPos = boutonAreaPos.y + vertSpace / 2.0f + i * (boutonNbPlayerSize.y + vertSpace);
			
			_boutonsNbPlayer[i] = new WButton(new Vector2f(boutonAreaPos.x + horizSpace / 2.0f,
					yPos), boutonNbPlayerSize, true, _valueBoutonNbPlayer, i+1);
			_boutonsNbPlayer[i].setText((i+1)+" joueurs");
			
			_boutonsNbHuman[i] = new WButton(new Vector2f(boutonAreaPos.x + 3*horizSpace / 2.0f + boutonNbPlayerSize.x,
					yPos), boutonNbHumanSize, true, _valueBoutonNbHuman, i+1);
			_boutonsNbHuman[i].setText((i+1)+" humain"+((i>0)?"s":""));
			
			_FieldPlayerName[i] = new WButton(new Vector2f(boutonAreaPos.x + 5*horizSpace / 2.0f + boutonNbPlayerSize.x + boutonNbHumanSize.x,
					yPos), fieldPlayerNameSize, false, new ButtonValue(0), 0);
			_FieldPlayerName[i].setText(_gameSettings.getPlayerNames()[i]);
			
			
		}
		
		// sera le bouton pour mettre 0 humains
		_boutonsNbPlayer[0] = new WButton(new Vector2f(boutonAreaPos.x + 3*horizSpace / 2.0f + boutonNbPlayerSize.x,
				boutonAreaPos.y + vertSpace / 2.0f - (boutonNbPlayerSize.y + vertSpace)), boutonNbPlayerSize, true, _valueBoutonNbHuman, 0);
		_boutonsNbPlayer[0].setText("0 humain");
		
		
		_boutonJouer = new WButton(new Vector2f(boutonAreaPos.x + 5*horizSpace / 2.0f + boutonNbPlayerSize.x + boutonNbHumanSize.x + fieldPlayerNameSize.x - boutonNbPlayerSize.x,
				window_size.y - windowPadding - boutonNbPlayerSize.y), boutonNbPlayerSize, true, ecran_actif, Application.SCR_GAME);
		_boutonJouer.setText("Jouer");
		

		_boutonRetour = new WButton(new Vector2f(boutonAreaPos.x + horizSpace / 2.0f,
				window_size.y - windowPadding - boutonNbPlayerSize.y), boutonNbPlayerSize, true, ecran_actif, Application.SCR_INDEX);
		_boutonRetour.setText("Menu");
		
		
		
	}

	
	

	
	public void onEvent(Event event)
	{
		for (int i=0; i<_boutonsNbPlayer.length; i++)
			_boutonsNbPlayer[i].onEvent(event);
		
		for (int i=0; i<_gameSettings.getNbPlayers(); i++)
			_boutonsNbHuman[i].onEvent(event);
		
		_boutonRetour.onEvent(event);
		_boutonJouer.onEvent(event);
		
	}
	

	public void update(Time elapsed_time)
	{
		_gameSettings.setNbPlayers(_valueBoutonNbPlayer.val);
		_gameSettings.setNbHumans(_valueBoutonNbHuman.val);
		_valueBoutonNbPlayer.val = _gameSettings.getNbPlayers();
		_valueBoutonNbHuman.val = _gameSettings.getNbHumans();
		
		_infosSettings.setString("Configuration choisie : "+_gameSettings.getNbPlayers()+" joueur"+((_gameSettings.getNbPlayers()>1)?"s":"")+
				" dont "+_gameSettings.getNbHumans()+" humain"+((_gameSettings.getNbHumans()>1)?"s":"")+
				" et "+(_gameSettings.getNbPlayers()-_gameSettings.getNbHumans())+" ordinateur"+(((_gameSettings.getNbPlayers()-_gameSettings.getNbHumans())>1)?"s":"")+".");
	}
	
	
	
	
	public void draw(RenderTarget target, RenderStates states)
	{
		target.draw(_titre, states);
		target.draw(_infosSettings, states);
		target.draw(_avertissement, states);
		target.draw(_boutonJouer, states);
		target.draw(_boutonRetour, states);
		for (int i=0; i<_headers.length; i++)
			target.draw(_headers[i], states);
		for (int i=0; i<_boutonsNbPlayer.length; i++)
			target.draw(_boutonsNbPlayer[i], states);
		
		for (int i=0; i<_gameSettings.getNbPlayers(); i++)
		{
			target.draw(_boutonsNbHuman[i], states);
			target.draw(_FieldPlayerName[i], states);
			
		}
		
	}
	
	
	
	
	
	
	
}
