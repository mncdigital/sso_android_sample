/*
 * Copyright (C) 2013 Mobs and Geeks
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file 
 * except in compliance with the License. You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the 
 * License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, 
 * either express or implied. See the License for the specific language governing permissions and 
 * limitations under the License.
 */

package com.mobsandgeeks.ui;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

import com.mobsandgeeks.ui.R.styleable;

import java.util.HashMap;
import java.util.Map;

/**
 * Subclass of {@link TextView} that supports the <code>customTypeface</code> attribute from XML.
 *
 * @author Ragunath Jawahar <rj@mobsandgeeks.com>
 */
public class TypefaceTextView extends TextView {

    /*
     * Caches typefaces based on their file path and name, so that they don't have to be created
     * every time when they are referenced.
     */
    private static Map<String, Typeface> mTypefaces;

    public TypefaceTextView(Context context) {
        super(context);
        // this(context, null);
    }

    public TypefaceTextView(Context context, AttributeSet attrs) {
        // this(context, attrs, 0);
        super(context, attrs);
        setTypefaceTextViewCustomFont(context, attrs);
    }

    public TypefaceTextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        setTypefaceTextViewCustomFont(context, attrs);
    }

    public TypefaceTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setTypefaceTextViewCustomFont(context, attrs);
    }

    private void setTypefaceTextViewCustomFont(Context context, AttributeSet attrs) {
        if(mTypefaces == null) {
            mTypefaces = new HashMap<String, Typeface>();
        }

        // prevent exception in Android Studio / ADT interface builder
        if(this.isInEditMode()) {
            return;
        }

        TypedArray array = context.obtainStyledAttributes(attrs, styleable.TypefaceTextView);
        if(array != null) {
            String typefaceAssetPath = array.getString(R.styleable.TypefaceTextView_customTypeface);

            if(typefaceAssetPath != null) {
                Typeface typeface = null;

                if(mTypefaces.containsKey(typefaceAssetPath)) {
                    typeface = mTypefaces.get(typefaceAssetPath);
                } else {
                    AssetManager assets = context.getAssets();
                    typeface = Typeface.createFromAsset(assets, typefaceAssetPath);
                    mTypefaces.put(typefaceAssetPath, typeface);
                }

                setTypeface(typeface);
            }
            array.recycle();
        }
    }

}
