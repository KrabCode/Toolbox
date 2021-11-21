package toolbox.tree.nodes.color;

import processing.core.PGraphics;
import toolbox.tree.nodes.FolderNode;
import toolbox.tree.nodes.ValueNode;

import static processing.core.PApplet.nf;

public class BrightnessNode extends ColorValueNode {



    public BrightnessNode(String path, ColorPickerFolderNode parentFolder) {
        super(path, parentFolder);
    }

    @Override
    protected void updateDrawInlineNode(PGraphics pg) {
        drawRightText(pg, nf(parentColorPickerFolder.color.br, 0, colorValueDigitsAfterDot));
    }
}
