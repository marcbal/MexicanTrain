package fr.univ_artois.iut_lens.mexican_train.gui;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

import org.jsfml.graphics.*;
import org.jsfml.system.*;
import org.jsfml.window.Mouse.Button;
import org.jsfml.window.event.*;

public class Widget implements Drawable {
	public static final Color _theme_def_bg_color = Color.WHITE;
	public static final Color _theme_def_border_color = Color.BLACK;
	
	public static final Color _theme_hov_bg_color = new Color(240, 240, 240);
	public static final Color _theme_hov_border_color = Color.BLACK;
	
	public static final Color _theme_clk_bg_color = new Color(220, 220, 220);
	public static final Color _theme_clk_border_color = Color.BLACK;
	
	public static final float _theme_border_tkness = -2;
	
	public static final Color _theme_txt_color = Color.BLACK;
	private static Font _theme_font = null;
	
	
	
	protected Vector2f _pos = new Vector2f(0, 0);
	protected Vector2f _size = new Vector2f(0, 0);
	
	protected boolean _enabled = true;
	protected boolean _mouseHover = false;
	protected boolean _mouseClick = false;
	protected boolean _mouseClickRight = false;
	
	
	
	
	
	public static Font getThemeFont()
	{
		if (_theme_font == null)
		{
			_theme_font = new Font();
			Path font_path = FileSystems.getDefault().getPath("ressources", "LiberationMono-Regular.ttf");
			try {
				_theme_font.loadFromFile(font_path);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return _theme_font;
	}
	
	public Widget (Vector2f pos, Vector2f size, boolean enabled)
	{
		getThemeFont();
		
		_pos = pos;
		_size = size;
		_enabled = enabled;
	}
	
	
	
	public void setEnabled(boolean enabled) { _enabled = enabled; }
	public boolean isEnabled() { return _enabled; }
	
	public Vector2f getPosition() { return _pos; }
	public void setPosition(Vector2f pos) { _pos = pos; }
	
	public Vector2f getSize() { return _size; }
	public void setSize(Vector2f size) { _size = size; }




	public void onEvent(Event event)
	{
		if (!_enabled)
			return;
		
		if (event.type == Event.Type.MOUSE_MOVED)
		{
			FloatRect r = new FloatRect(_pos, _size);
			_mouseHover = r.contains(new Vector2f(event.asMouseEvent().position));
		}
		else if (event.type == Event.Type.MOUSE_BUTTON_PRESSED)
		{
			if (event.asMouseButtonEvent().button == Button.LEFT && _mouseHover)
				_mouseClick = true;
			
			if (event.asMouseButtonEvent().button == Button.RIGHT && _mouseHover)
				_mouseClickRight = true;
		}
		else if (event.type == Event.Type.MOUSE_BUTTON_RELEASED)
		{
			if (event.asMouseButtonEvent().button == Button.LEFT)
				_mouseClick = false;

			if (event.asMouseButtonEvent().button == Button.RIGHT)
				_mouseClickRight = false;
		}
	}
	
	
	
	@Override
	public void draw(RenderTarget target, RenderStates states) {

	}

}
