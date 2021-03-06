/*
 * Dice heroes is a turn based rpg-strategy game where characters are dice.
 * Copyright (C) 2016 Vladislav Protsenko
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.vlaaad.dice.game.tutorial;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.vlaaad.common.tutorial.TutorialTask;
import com.vlaaad.common.ui.GameWindow;
import com.vlaaad.common.ui.WindowListener;

/**
 * Created 12.11.13 by vlaaad
 */
public class AllowOpenWindows extends TutorialTask {

    private Class<? extends GameWindow> type;

    public AllowOpenWindows() {
    }

    public AllowOpenWindows(Class<? extends GameWindow> type) {
        super();
        this.type = type;
    }

    @Override public void start(Callback callback) {
        Stage stage = resources.get("stage");
        if (resources.has("restrictOpenWindowsListener")) {
            WindowListener listener = resources.remove("restrictOpenWindowsListener");
            stage.removeCaptureListener(listener);
        }
        if (type == null) {
            callback.taskEnded();
            return;
        }
        WindowListener listener = new WindowListener() {
            @Override protected void show(WindowEvent event) {
                if (!type.isInstance(event.getWindow())) {
                    event.cancel();
                }
            }
        };
        stage.addCaptureListener(listener);
        resources.put("restrictOpenWindowsListener", listener);
        callback.taskEnded();
    }
}
