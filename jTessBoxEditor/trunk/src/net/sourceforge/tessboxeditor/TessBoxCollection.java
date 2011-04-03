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

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class TessBoxCollection {

    private List<TessBox> list;

    public TessBoxCollection() {
        list = new ArrayList<TessBox>();
    }

    void add(TessBox box) {
        list.add(box);
    }

    void add(int index, TessBox box) {
        list.add(index, box);
    }

    void deselectAll() {
        for (TessBox box : list) {
            box.setSelected(false);
        }
    }

    List<TessBox> toList() {
        return list;
    }

    List<TessBox> toList(short page) {
        List<TessBox> subList = new ArrayList<TessBox>();
        for (TessBox box : list) {
            if (box.page == page) {
                subList.add(box);
            }
        }
        return subList;
    }

    void clear() {
        list.clear();
    }

    TessBox hitObject(Point p, short page) {
        for (TessBox box : list) {
            if (box.page == page && box.contains(p)) {
                return box;
            }
        }
        return null;
    }

    List<TessBox> getSelectedBoxes() {
        List<TessBox> selected = new ArrayList<TessBox>();
        for (TessBox box : list) {
            if (box.isSelected()) {
                selected.add(box);
            }
        }
        return selected;
    }

    boolean remove(TessBox box) {
        return list.remove(box);
    }

    TessBox remove(int index) {
        return list.remove(index);
    }

    /**
     * Gets coordinate data for each page.
     * @param page
     * @return
     */
    List<String[]> getTableDataList(short page) {
        List<String[]> dataList = new ArrayList<String[]>();
        for (TessBox box : list) {
            if (box.page == page) {
                String[] item = new String[5];
                item[0] = box.chrs;
                item[1] = String.valueOf(box.rect.x);
                item[2] = String.valueOf(box.rect.y);
                item[3] = String.valueOf(box.rect.width);
                item[4] = String.valueOf(box.rect.height);
                dataList.add(item);
            }
        }
        return dataList;
    }
}
