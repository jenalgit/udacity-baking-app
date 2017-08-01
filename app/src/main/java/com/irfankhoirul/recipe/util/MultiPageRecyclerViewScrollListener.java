/*
 * Copyright 2017.  Irfan Khoirul Muhlishin
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.irfankhoirul.recipe.util;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class MultiPageRecyclerViewScrollListener extends RecyclerView.OnScrollListener {

    private LoadNextListener listener;
    private boolean loaded;

    public MultiPageRecyclerViewScrollListener(boolean loaded, LoadNextListener listener) {
        this.loaded = loaded;
        this.listener = listener;
    }

    public void isLoading(boolean loading) {
        this.loaded = loading;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        RecyclerView.Adapter adapter = recyclerView.getAdapter();
        int totalItemCount = adapter.getItemCount();
        int lastVisibleItemPosition = ((LinearLayoutManager) recyclerView.getLayoutManager()).findLastVisibleItemPosition();

        if ((lastVisibleItemPosition + 1) == totalItemCount) {
            if (!loaded) {
                loaded = true;
                listener.onStartLoadNext();
            }
        }
    }

    public interface LoadNextListener {
        void onStartLoadNext();
    }
}