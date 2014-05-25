package fr.univ_artois.iut_lens.mexican_train.gui;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

import org.jsfml.graphics.Color;
import org.jsfml.graphics.Font;
import org.jsfml.graphics.RenderStates;
import org.jsfml.graphics.RenderTarget;
import org.jsfml.graphics.Text;
import org.jsfml.system.Time;
import org.jsfml.system.Vector2f;
import org.jsfml.system.Vector2i;

public class ScreenDebug extends Screen {
	
	private Runtime runtime = Runtime.getRuntime();
	private Text _infos = new Text();
	
	private boolean _showDebugger = false;

	public ScreenDebug(Vector2i window_size) {
		super(window_size);
		

		Font f = new Font();
		Path font_path = FileSystems.getDefault().getPath("ressources", "LiberationMono-Regular.ttf");
		try {
			f.loadFromFile(font_path);
		} catch (IOException e) {
			e.printStackTrace();
		}
		_infos.setFont(f);
		_infos.setColor(Color.BLACK);
		_infos.setCharacterSize(15);
	}
	
	
	
	public void toggleDebugger() { _showDebugger = !_showDebugger; }
	
	
	

	
	public void update(Time elapsed_time)
	{
		if (!_showDebugger)
			return;
		
		_infos.setString("Train Mexicain - Application Java par Marc Baloup\n"+
					"Université d'Artois - IUT de Lens - Année 2014\n"+
					"Mémoire : "+runtime.freeMemory()+" libre - "+runtime.totalMemory()+" utilisable - "+runtime.maxMemory()+" maximale\n"+
					"Frame time : "+elapsed_time.asSeconds()+"s - "+(int)(1/elapsed_time.asSeconds())+" FPS");
		_infos.setPosition(new Vector2f(0,0));
	}
	
	
	
	
	
	@Override
	public void draw(RenderTarget target, RenderStates states)
	{
		if (!_showDebugger)
			return;
		target.draw(_infos, states);
	}
	

}
