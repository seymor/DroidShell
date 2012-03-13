package org.droidshell;

import org.droidshell.opengl.shader.ShaderFactory;
import org.droidshell.opengl.shader.program.ShaderProgramFactory;
import org.droidshell.opengl.texture.TextureFactory;
import org.droidshell.opengl.vertexbuffer.VertexBufferFactory;
import org.droidshell.screen.ScreenManager;

import android.app.Activity;
import android.os.Bundle;

public class DroidShellActivity extends Activity {
    /** Called when the activity is first created. */
	private DroidShellView view;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        ScreenManager.init(this);
        ScreenManager.fullScreen();
        
        ShaderFactory.init(this);
        
        ShaderProgramFactory.init();
        
        TextureFactory.init(this);
        
        VertexBufferFactory.init();
        
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
    
    @Override
    public void onBackPressed() {
            super.onBackPressed();
            this.finish();
            android.os.Process.killProcess(android.os.Process.myPid()) ;
    }

}