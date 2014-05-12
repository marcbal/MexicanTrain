package fr.univ_artois.iut_lens.mexican_train.gui;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

import org.jsfml.graphics.*;
import org.jsfml.system.*;
import org.jsfml.window.event.Event;

public class ScreenIndex extends Screen {
	
	private WButton[] _boutons = new WButton[3];
	private Text _title = new Text();

	public ScreenIndex(Vector2i window_size, ButtonValue ecran_actif) {
		super(window_size);
		
		
		
		
		// --------------- Titre ------------------
		Font title_font = new Font();
		Path font_path = FileSystems.getDefault().getPath("ressources", "LiberationMono-Regular.ttf");
		try {
			title_font.loadFromFile(font_path);
		} catch (IOException e) {
			e.printStackTrace();
		}
		_title.setCharacterSize(70);
		_title.setFont(title_font);
		_title.setColor(Color.BLACK);
		_title.setStyle(Text.BOLD);
		_title.setString("Train Mexicain");

		// on centre le texte
		_title.setOrigin(0, 0);
		_title.setPosition(0, 0);
		_title.setOrigin(_title.getGlobalBounds().left + _title.getGlobalBounds().width/2.0f,
				_title.getGlobalBounds().top + _title.getGlobalBounds().height/2.0f);
		_title.setPosition(window_size.x/2.0f,
						  300/2.0f);
		
		// ----------------------------------------
		
		
		
		// ------ boutons du menu principal -------
		Vector2f v0 = new Vector2f(0, 0);
		_boutons[0] = new WButton(v0, v0, true, ecran_actif, Application.SCR_GAME);
		_boutons[1] = new WButton(v0, v0, true, ecran_actif, Application.SCR_PLAYER_CHOICE);
		_boutons[2] = new WButton(v0, v0, true, ecran_actif, Application.BUTTON_QUIT);
		
		_boutons[0].setText("Jeu rapide");
		_boutons[1].setText("Choix des joueurs");
		_boutons[2].setText("Quitter");
		
		for (int i=0; i<_boutons.length; i++)
		{	// positionnement des boutons
			float bouton_width = 200;
			float margin = (window_size.x - bouton_width * _boutons.length) / (1 + (float) _boutons.length);
			_boutons[i].setSize(new Vector2f(bouton_width, 50));
			_boutons[i].setPosition(new Vector2f(margin + (bouton_width + margin) * i, 300));
		}
		
		
		// ----------------------------------------
		
		
	}

	
	
	public void onEvent(Event event)
	{

		for (int i=0; i<_boutons.length; i++)
			_boutons[i].onEvent(event);
	}
	

	public void update(Time elapsed_time)
	{
		
	}
	
	
	
	
	public void draw(RenderTarget target, RenderStates states) {
		for (int i=0; i<_boutons.length; i++)
			target.draw(_boutons[i], states);
		target.draw(_title, states);
	}
	

}
