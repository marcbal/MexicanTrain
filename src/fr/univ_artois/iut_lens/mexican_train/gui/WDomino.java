package fr.univ_artois.iut_lens.mexican_train.gui;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

import org.jsfml.graphics.*;
import org.jsfml.system.Time;
import org.jsfml.system.Vector2f;
import org.jsfml.window.Mouse.Button;
import org.jsfml.window.event.Event;

import fr.univ_artois.iut_lens.mexican_train.game.Domino;

public class WDomino extends Widget {
	private Domino _domino;
	
	private static Texture[][] _images = new Texture[13][13];
	private static Texture[] _imagesEmpty = new Texture[2];
	private Texture _texture;
	private Sprite _sprite = new Sprite();
	
	
	// pour les dominos cliquables (_enabled = true)
	private boolean _clicked = false;
	private RectangleShape _clickedShape = new RectangleShape();
	private boolean _swappable;
	
	// pour l'animation d'arrière plan
	private boolean _autoAnimated = false;
	private Vector2f _speed = new Vector2f(0, 0);
	private float _rotSpeed = 0;
	
	
	public WDomino(Vector2f pos, Vector2f size, boolean enabled, Domino d, boolean autoAnimated, boolean swappable) {
		super(pos, size, enabled);
		_domino = d;
		_autoAnimated = autoAnimated;
		_swappable = swappable;
		
		// --- Chargement des images des dominos en mémoire ---
		if (_images[0][0] == null)
		{
			for (int i=0; i<13; i++)
				for (int j=0; j<13; j++)
				{
					_images[i][j] = new Texture();
					Path path = FileSystems.getDefault().getPath("ressources/dominos", i+"-"+j+".png");
					try {
						_images[i][j].loadFromFile(path);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			

			_imagesEmpty[0] = new Texture();
			Path path = FileSystems.getDefault().getPath("ressources/dominos", "null.png");
			try {
				_imagesEmpty[0].loadFromFile(path);
			} catch (IOException e) {
				e.printStackTrace();
			}
			_imagesEmpty[1] = new Texture();
			path = FileSystems.getDefault().getPath("ressources/dominos", "reverse.png");
			try {
				_imagesEmpty[1].loadFromFile(path);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
		// ----------------------------------------------------
		
		updateTexture();
		
		_clickedShape.setFillColor(Color.TRANSPARENT);
		_clickedShape.setOutlineColor(Color.RED);
		_clickedShape.setOutlineThickness(-1.7f);
		
		
		if (_autoAnimated)
		{
			FloatRect spr_rect = _sprite.getLocalBounds();
			setOrigin(new Vector2f(spr_rect.width/2.0f, spr_rect.height/2.0f));
		}
		
		
		setPosition(pos);
		setSize(size);
	}
	
	
	
	

	
	
	public void update(Time elapsed_time)
	{
		if (_autoAnimated)
		{	// animation automatique du domino
			setPosition(Vector2f.add(getPosition(), Vector2f.mul(_speed, elapsed_time.asSeconds())));
			_sprite.rotate(_rotSpeed * elapsed_time.asSeconds());
		}
		
		
		updateTexture();
		
		
		
	}
	
	
	public void onEvent(Event event)
	{
		if (!_enabled)
			return;
		
		
		if (event.type == Event.Type.MOUSE_BUTTON_RELEASED &&
				event.asMouseButtonEvent().button == Button.LEFT &&
				_mouseHover && _mouseClick)
		{
			_clicked = !_clicked;
		}
			
		else if (event.type == Event.Type.MOUSE_BUTTON_RELEASED &&
				event.asMouseButtonEvent().button == Button.RIGHT &&
				_mouseHover && _mouseClickRight)
		{
			if (_swappable && _domino != null)
			{
				_domino.swap();
				System.out.println(_domino+" a été swap");
			}
		}
		
		
		super.onEvent(event);
	}
	
	
	
	public void setSpeed(Vector2f speed) { _speed = speed; }
	public void setRotationSpeed(float rot) { _rotSpeed = rot; }
	
	
	public void setPosition(Vector2f pos) {
		super.setPosition(pos);
		_sprite.setPosition(_pos);
		_clickedShape.setPosition(_pos);
	}
	
	public void setOrigin(Vector2f origin) { _sprite.setOrigin(origin); }
	
	public void setSize(Vector2f size) {
		FloatRect spr_rect = _sprite.getLocalBounds();
		float scale = size.x / spr_rect.width;
		if (scale > size.y / spr_rect.height)
			scale = size.x / spr_rect.width;
		setScale(scale);
		_clickedShape.setSize(size);
		
	}
	
	public void setScale(float scale) { _sprite.setScale(scale, scale); }

	public Sprite asSprite() { return _sprite; }
	public boolean isClicked() { return _clicked; }
	
	public void unclick() { _clicked = false; }
	
	
	public void setDomino(Domino d)
	{
		_domino = d;
		updateTexture();
	}
	public Domino getDomino() { return _domino; }
	
	
	
	private void updateTexture()
	{
		if (_domino == null)
			_texture = _imagesEmpty[0];
		else if (_domino.isHidden())
			_texture = _imagesEmpty[1];
		else
			_texture = _images[_domino.getV1()][_domino.getV2()];
		_sprite.setTexture(_texture);
	}
	
	
	
	
	
	
	
	
	public void draw(RenderTarget target, RenderStates states)
	{
		target.draw(_sprite, states);
		if (_clicked && _enabled)
			target.draw(_clickedShape, states);
	}
	
}
