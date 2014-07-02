package com.scancook.eeb;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class RecipeFoundActivity extends Activity {
	
	private TextView mTvRecipeText;
	private TextView mTvRecipeName;
	private Button mBtnNext;
	private Button mBtnPrev;
	private int mRecipeCounter = 0;
	private ArrayList<Recipe> mRecipes;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.recipe_found);
		mRecipes = new ArrayList<Recipe>();
		String recipe = getIntent().getStringExtra("RESULT");
		recipeParsing(recipe);
		mTvRecipeText = (TextView) findViewById(R.id.tv_found_recipe);
		mTvRecipeText.setMovementMethod(new ScrollingMovementMethod());
		mTvRecipeName = (TextView) findViewById(R.id.tv_recipe_name);
		mTvRecipeName.setMovementMethod(new ScrollingMovementMethod());
		mBtnNext = (Button) findViewById(R.id.bt_right);
		mBtnNext.setMovementMethod(new ScrollingMovementMethod());
		mBtnPrev = (Button) findViewById(R.id.bt_left);
		mBtnPrev.setMovementMethod(new ScrollingMovementMethod());
		
		mBtnNext.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				if (mRecipeCounter < mRecipes.size()-1) {
					changeRecipe(++mRecipeCounter);
				}
			}
		});
		
		mBtnPrev.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				if (mRecipeCounter > 0) {
					changeRecipe(--mRecipeCounter);
				}
			}
		});
	}
	
	private void recipeParsing(String result) {
		try {
			JSONArray recipeArray = new JSONArray(result);
			for (int i = 0; i < recipeArray.length(); i ++) {
				JSONObject singleRecipe = recipeArray.getJSONObject(i);
				String name = singleRecipe.getString("Rezept");
				String recipe = singleRecipe.getString("Beschreibung");
				mRecipes.add(new Recipe(name, recipe));
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void onResume() {
		changeRecipe(mRecipeCounter);
	}
	
	private void  changeRecipe(int position) {
		mTvRecipeName.setText(mRecipes.get(position).Name);
		mTvRecipeText.setText(mRecipes.get(position).Recipe);
	}
	
	private class Recipe {
		public String Name;
		public String Recipe;
		
		public Recipe(String name, String recipe) {
			Name = name;
			Recipe = recipe;
		}
	}
}