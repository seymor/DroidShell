package org.droidshell;

import org.droidshell.engine.Engine;
import org.droidshell.engine.EngineView;
import org.droidshell.screen.ScreenManager;

import android.app.Activity;
import android.os.Bundle;

public class DroidShellActivity extends Activity {

	private EngineView engineView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Engine engine = new Engine(DroidShellActivity.this);

		ScreenManager.fullScreen();
		ScreenManager.ignoreOrientationChanges();
		ScreenManager.setOrientation(ScreenManager.LANDSCAPE);
		engineView = new EngineView(engine);

		setContentView(engineView);
	}

	@Override
	public void onPause() {
		super.onPause();

		engineView.onPause();

	}

	@Override
	public void onResume() {
		super.onResume();

		engineView.onResume();
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		this.finish();
		android.os.Process.killProcess(android.os.Process.myPid());
	}

}