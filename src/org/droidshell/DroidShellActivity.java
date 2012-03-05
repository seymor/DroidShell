package org.droidshell;

import org.droidshell.opengl.shader.ShaderManager;
import org.droidshell.opengl.texture.TextureManager;
import org.droidshell.screen.ScreenManager;

import android.app.Activity;
import android.os.Bundle;

public class DroidShellActivity extends Activity {
    /** Called when the activity is first created. */
	private DroidShellView view;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        TextureManager.setContext(this);
        //TextureManager.loadImages();
        
        ScreenManager.init(this);
        
        ShaderManager.setContext(this);
        
        view = new DroidShellView(this);
        
        setContentView(view);
    }
    
    @Override
    public void onPause() {
        super.onPause();
        
        view.onPause();
    }
    
    @Override
    public void onResume() {
        super.onResume();
        
        view.onResume();
    }

}