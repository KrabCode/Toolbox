package toolbox.windows.controls;

import processing.core.PGraphics;
import processing.core.PVector;
import toolbox.GlobalState;
import toolbox.Palette;
import toolbox.tree.Node;
import toolbox.windows.Window;

import static processing.core.PApplet.println;
import static processing.core.PConstants.CENTER;

public class ButtonWindow extends Window {

    public ButtonWindow(Node node, PVector pos) {
        super(node, pos, new PVector(GlobalState.cell * 4, GlobalState.cell * 2));
    }

    boolean pMousePressed = false;

    @Override
    protected void drawContent(PGraphics pg) {
        updateButtonValue();
        boolean currentValue = node.valueBoolean;
        String text = "button";
        if(pMousePressed){
            pg.fill(Palette.buttonArmedContentFill);
        }else if (currentValue) {
            pg.fill(Palette.buttonPressedContentFill);
        } else {
            pg.fill(Palette.buttonUnpressedContentFill);
        }
        pg.noStroke();
        pg.rect(0, cell, windowSize.x, windowSize.y - cell);
        fillTextColorBasedOnFocus(pg);
        pg.textAlign(CENTER, CENTER);
        pg.text(text, pg.width * 0.5f, pg.height * 0.5f + GlobalState.font.getSize() * 0.5f);
    }

    private void updateButtonValue() {
        boolean mousePressed = GlobalState.app.mousePressed;
        float x = GlobalState.app.mouseX;
        float y = GlobalState.app.mouseY;
        boolean isMouseInside = isPointInsideContent(x,y);
        if(isMouseInside){
            node.valueBoolean = pMousePressed && !mousePressed;
        }
        pMousePressed = mousePressed && isMouseInside;
    }
}