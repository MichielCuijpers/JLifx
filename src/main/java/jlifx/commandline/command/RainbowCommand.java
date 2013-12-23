package jlifx.commandline.command;

import java.awt.Color;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import jlifx.bulb.Bulb;
import jlifx.commandline.AbstractBulbCommand;

public class RainbowCommand extends AbstractBulbCommand {

    @Override
    public boolean execute(Collection<Bulb> bulbs, String[] commandArgs, PrintStream out) throws Exception {
        startKeyListenerThread(out);
        List<Color> spectrumColors = getSpectrumColors();
        int i = 0;
        while (!isInterrupted()) {
            if (i >= spectrumColors.size()) {
                i = 0;
            }
            for (Bulb bulb : bulbs) {
                Color color = spectrumColors.get(i);
                bulb.colorize(color, 3);
            }
            Thread.sleep(3000);
            i++;
        }
        return true;
    }

    List<Color> getSpectrumColors() {
        List<Color> colors = new ArrayList<Color>();
        for (int i = 0; i < 16; i++) {
            double factor = ((2 * Math.PI) * i) / 16;
            int red = (int)(Math.sin(factor + 0) * 127 + 128);
            int green = (int)(Math.sin(factor + 2) * 127 + 128);
            int blue = (int)(Math.sin(factor + 4) * 127 + 128);
            colors.add(new Color(red, green, blue));
        }
        return colors;
    }

}