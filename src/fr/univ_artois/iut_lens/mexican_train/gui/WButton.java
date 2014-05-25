package fr.univ_artois.iut_lens.mexican_train.gui;

import org.jsfml.graphics.*;
import org.jsfml.system.*;
import org.jsfml.window.Mouse.Button;
import org.jsfml.window.event.*;

public class WButton extends Widget {
	private ButtonValue _valToChange;
	private int _newVal;
	
	private RectangleShape _shape_def = new RectangleShape();
	private RectangleShape _shape_hov = new RectangleShape();
	private RectangleShape _shape_clk = new RectangleShape();
	
	private Text _text = new Text();
	
	

	public WButton(Vector2f pos, Vector2f size, boolean enabled, ButtonValue changedVal, int newVal) {
		super(pos, size, enabled);
		_valToChange = changedVal;
		_newVal = newVal;
		
		_text.setFont(getThemeFont());
		_text.setColor(_theme_txt_color);
		_text.setCharacterSize(17);
		_shape_def.setFillColor(_theme_def_bg_color);
		_shape_def.setOutlineColor(_theme_def_border_color);
		_shape_def.setOutlineThickness(_theme_border_tkness);
		_shape_clk.setFillColor(_theme_clk_bg_color);
		_shape_clk.setOutlineColor(_theme_clk_border_color);
		_shape_clk.setOutlineThickness(_theme_border_tkness);
		_shape_hov.setFillColor(_theme_hov_bg_color);
		_shape_hov.setOutlineColor(_theme_hov_border_color);
		_shape_hov.setOutlineThickness(_theme_border_tkness);
		
		
		_text.setString("");
		setPosition(pos);
		setSize(size);
		
		
		
	}
	
	
	public void setPosition(Vector2f pos)
	{
		super.setPosition(pos);
		_shape_def.setPosition(pos);
		_shape_hov.setPosition(pos);
		_shape_clk.setPosition(pos);
		updateTextPosition();
	}
	
	public void setSize(Vector2f size)
	{
		super.setSize(size);
		_shape_def.setSize(_size);
		_shape_hov.setSize(_size);
		_shape_clk.setSize(_size);
		updateTextPosition();
	}
	
	public void setText (String text)
	{
		_text.setString(text);
		updateTextPosition();
	}
	
	
	
	public void onEvent(Event event)
	{
		if (!_enabled)
			return;
		
		
		if (event.type == Event.Type.MOUSE_BUTTON_RELEASED &&
				event.asMouseButtonEvent().button == Button.LEFT &&
				_mouseHover && _mouseClick)
			_valToChange.val = _newVal;
		
		
		super.onEvent(event);
		
		
		
	}
	
	
	
	protected void updateTextPosition()
	{
		_text.setOrigin(0, 0);
		_text.setPosition(0, 0);
		_text.setOrigin(Math.round(_text.getGlobalBounds().left + _text.getGlobalBounds().width/2.0f),
				Math.round(_text.getGlobalBounds().top + _text.getGlobalBounds().height/2.0f));
		_text.setPosition(Math.round(_pos.x+_size.x/2.0f),
				Math.round(_pos.y+_size.y/2.0f));
	}
	

	@Override
	public void draw(RenderTarget target, RenderStates states)
	{
		if (_mouseClick)
			target.draw(_shape_clk, states);
		else if (_mouseHover)
			target.draw(_shape_hov, states);
		else
			target.draw(_shape_def, states);
		target.draw(_text, states);
	}
	

}
