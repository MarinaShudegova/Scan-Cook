package com.scancook.eeb;

import android.app.Activity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

public class RecipeFoundActivity extends Activity {
	
	TextView m_tvFoundRecipes;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.recipe_found);
		String recipe = getIntent().getStringExtra("RESULT");
		m_tvFoundRecipes = (TextView) findViewById(R.id.tv_found_recipe);
		m_tvFoundRecipes.setMovementMethod(new ScrollingMovementMethod());
		m_tvFoundRecipes.setText(recipe);
	}
}