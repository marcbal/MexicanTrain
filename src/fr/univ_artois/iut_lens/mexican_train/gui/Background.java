package fr.univ_artois.iut_lens.mexican_train.gui;

import java.util.Random;

import org.jsfml.graphics.*;
import org.jsfml.system.*;
import org.jsfml.window.event.Event;

import fr.univ_artois.iut_lens.mexican_train.game.Domino;

public class Background extends Screen {

	private WDomino[] _dominos = new WDomino[10];
	private RectangleShape _opacityRectangle = new RectangleShape();
	private boolean _enable = true;
	private boolean _animate = true;
	
	
	public Background(Vector2i window_size) {
		super(window_size);
		_opacityRectangle.setPosition(0, 0);
		_opacityRectangle.setSize(new Vector2f(window_size));
		_opacityRectangle.setFillColor(new Color(255, 255, 255, 223));
		
		Random rand = new Random();
		for (int i=0; i<_dominos.length; i++)
		{
			float size = ((float) (_dominos.length-i)/_dominos.length)*200+300;
			_dominos[i] = new WDomino(new Vector2f(rand.nextFloat()*(window_size.x+500)-250, rand.nextFloat()*(window_size.y+500)-250),
									  new Vector2f(size, size),
									  false,
									  new Domino(rand.nextInt(13), rand.nextInt(13)),
									  true,
									  false);
			_dominos[i].setSpeed(new Vector2f(rand.nextFloat()*20-10, rand.nextFloat()*20-10));
			_dominos[i].setRotationSpeed(rand.nextFloat()*10-5);

			_dominos[i].asSprite().setRotation(rand.nextFloat()*180);
		}
	}
	
	
	
	public void update(Time elapsed_time)
	{
		if (!_animate)
			return;
		float externalLimit = 250;
		for (int i=0; i<_dominos.length; i++)
		{
			_dominos[i].update(elapsed_time);
			float x = _dominos[i].getPosition().x, y = _dominos[i].getPosition().y;
			if (x > _window_size.x + externalLimit)
				x -= _window_size.x + 2*externalLimit;
			if (x < -externalLimit)
				x += _window_size.x + 2*externalLimit;
			if (y > _window_size.y + externalLimit)
				y -= _window_size.y + 2*externalLimit;
			if (y < -externalLimit)
				y += _window_size.y + 2*externalLimit;
			_dominos[i].setPosition(new Vector2f(x, y));
		}
	}
	
	
	public void onEvent(Event event)
	{
		
	}
	
	
	public void toggleEnable() { _enable = !_enable; }
	public void toggleAnimate() { _animate = !_animate; }
	
	
	
	
	public void draw(RenderTarget target, RenderStates states)
	{
		if (!_enable)
			return;
		for (int i=0; i<_dominos.length; i++)
			target.draw(_dominos[i], states);
		target.draw(_opacityRectangle, states);
	}
	
}
