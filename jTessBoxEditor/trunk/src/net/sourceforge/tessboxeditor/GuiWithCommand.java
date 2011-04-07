/**
 * Copyright @ 2011 Quan Nguyen
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.sourceforge.tessboxeditor;

import java.awt.Rectangle;
import java.util.List;
import javax.swing.JOptionPane;

public class GuiWithCommand extends GuiWithMRU {

    @Override
    void mergeAction() {
        List<TessBox> selected = boxes.getSelectedBoxes();
        if (selected.size() <= 1) {
            return;
        }

        int minX = Integer.MAX_VALUE, minY = Integer.MAX_VALUE, maxX = 0, maxY = 0;

        String chrs = null;
        short page = 0;
        int index = 0;

        for (TessBox box : selected) {
            chrs = box.chrs;
            page = box.page;
            index = this.boxes.toList(imageIndex).indexOf(box);
            minX = Math.min(minX, box.rect.x);
            minY = Math.min(minY, box.rect.y);
            maxX = Math.max(maxX, box.rect.x + box.rect.width);
            maxY = Math.max(maxY, box.rect.y + box.rect.height);
            this.boxes.remove(box);
        }

        if (chrs != null) {
            TessBox newBox = new TessBox(chrs, new Rectangle(minX, minY, maxX - minX, maxY - minY), page);
            newBox.setSelected(true);
            boxes.add(index, newBox);
            tableModel.setDataVector(boxes.getTableDataList(page).toArray(new String[0][5]), headers);
            this.jTable.setRowSelectionInterval(index, index);
            Rectangle rect = this.jTable.getCellRect(index, 0, true);
            this.jTable.scrollRectToVisible(rect);
        }

        this.jLabelImage.repaint();
        updateSave(true);
    }

    @Override
    void splitAction() {
        List<TessBox> selected = boxes.getSelectedBoxes();
        if (selected.size() <= 0) {
            return;
        } else if (selected.size() > 1) {
            JOptionPane.showMessageDialog(this, "Select only one box for Split operation.");
            return;
        }

        TessBox box = selected.get(0);
        int index = this.boxes.toList(imageIndex).indexOf(box);
        box.rect.width /= 2;
        tableModel.setValueAt(String.valueOf(box.rect.x + box.rect.width), index, 3);

        TessBox newBox = new TessBox(box.chrs, new Rectangle(box.rect), box.page);
        newBox.rect.x += newBox.rect.width;
        newBox.setSelected(true);
        boxes.add(index, newBox);
        Object[] newRow = {newBox.chrs, newBox.rect.x, newBox.rect.y, newBox.rect.x + newBox.rect.width, newBox.rect.y + newBox.rect.height};
        tableModel.insertRow(index, newRow);

        this.jLabelImage.repaint();
        updateSave(true);
    }

    @Override
    void deleteAction() {
        List<TessBox> selected = boxes.getSelectedBoxes();
        if (selected.size() <= 0) {
            return;
        }

        for (TessBox box : selected) {
            int index = this.boxes.toList(imageIndex).indexOf(box);
            this.boxes.remove(index);
            tableModel.removeRow(index);
        }

        resetReadout();
        this.jLabelImage.repaint();
        updateSave(true);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new GuiWithCommand().setVisible(true);
            }
        });
    }
}
