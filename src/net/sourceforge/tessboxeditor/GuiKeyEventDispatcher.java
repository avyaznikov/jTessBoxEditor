package net.sourceforge.tessboxeditor;

import java.awt.Component;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;

import javax.swing.JSpinner;
import javax.swing.JTextField;

public class GuiKeyEventDispatcher implements KeyEventDispatcher {

	private Gui gui;

	GuiKeyEventDispatcher(Gui gui) {
		this.gui = gui;
	}

	private void inc(JSpinner s) {
		if (s == gui.jSpinnerX || s == gui.jSpinnerY) {
			if (!gui.invertControls) {
				s.setValue(Math.max(0, ((Integer) s.getValue())
						- gui.movementMultiplier));
			} else {
				s.setValue(((Integer) s.getValue()) + gui.movementMultiplier);
			}
		} else {
			s.setValue(((Integer) s.getValue()) + gui.movementMultiplier);
		}
	}

	private void inc(JSpinner s, int max) {
		if ((s == gui.jSpinnerX || s == gui.jSpinnerY) && !gui.invertControls) {
			s.setValue(Math.max(0, ((Integer) s.getValue())
					- gui.movementMultiplier));
		} else {
			s.setValue(((Integer) s.getValue()) + gui.movementMultiplier);
		}
	}

	private void dec(JSpinner s) {
		if ((s == gui.jSpinnerX || s == gui.jSpinnerY) && !gui.invertControls) {
			s.setValue(((Integer) s.getValue()) + gui.movementMultiplier);
		} else {
			s.setValue(Math.max(0, ((Integer) s.getValue())
					- gui.movementMultiplier));
		}
	}

	@Override
	public boolean dispatchKeyEvent(KeyEvent e) {

		if (e.getID() != KeyEvent.KEY_TYPED) {
			return false;
		}

		Component focusOwner = KeyboardFocusManager
				.getCurrentKeyboardFocusManager().getFocusOwner();

		if ((focusOwner instanceof JSpinner)
				|| (focusOwner instanceof JTextField)) {
			return false;
		}

		if (gui.jLabelCharacter.hasFocus()) {
			return false;
		}

		if (!gui.jPanelBoxView.isShowing()) {
			return false;
		}

		if (e.isShiftDown()) {
			gui.movementMultiplier = 10;
		} else {
			gui.movementMultiplier = 1;

		}

		char c = Character.toLowerCase(e.getKeyChar());

		if (eq(c, 'w')) {
			inc(gui.jSpinnerY);
		} else if (eq(c, 's')) {
			dec(gui.jSpinnerY);
		} else if (eq(c, 'd')) {
			dec(gui.jSpinnerX);
		} else if (eq(c, 'a')) {
			inc(gui.jSpinnerX);
		} else if (eq(c, 'q')) {
			dec(gui.jSpinnerW);
		} else if (eq(c, 'e')) {
			inc(gui.jSpinnerW);
		} else if (eq(c, 'r')) {
			dec(gui.jSpinnerH);
		} else if (eq(c, 'f')) {
			inc(gui.jSpinnerH);
		} else if (eq(c, ',')) {
			gui.jButtonPrev.doClick();
		} else if (eq(c, '.')) {
			gui.jButtonNext.doClick();
		} else if (eq(c, 'x')) {
			gui.jTextFieldCharacter.requestFocus();
			gui.jTextFieldCharacter.selectAll();
		} else {
			return false;
		}

		return true;
	}

	private boolean eq(char keySymbol, char engSymbol) {
		return keySymbol == engSymbol || keySymbol == getRusSymbol(engSymbol);
	}

	private char getRusSymbol(char engSymbol) {
		switch (engSymbol) {
		case 'q':
			return 'й';
		case 'w':
			return 'ц';
		case 'e':
			return 'у';
		case 'a':
			return 'ф';
		case 's':
			return 'ы';
		case 'd':
			return 'в';
		case 'z':
			return 'я';
		case 'x':
			return 'ч';
		case 'c':
			return 'с';
		case 'r':
			return 'к';
		case 'f':
			return 'а';
		case ',':
			return 'б';
		case '.':
			return 'ю';
		}
		return engSymbol;
	}
}
