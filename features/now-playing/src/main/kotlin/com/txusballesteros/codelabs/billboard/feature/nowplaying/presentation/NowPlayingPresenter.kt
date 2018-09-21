/*
 * Copyright Txus Ballesteros 2018 (@txusballesteros)
 *
 * This file is part of some open source application.
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 *
 * Contact: Txus Ballesteros <txus.ballesteros@gmail.com>
 */
package com.txusballesteros.codelabs.billboard.feature.nowplaying.presentation

import com.txusballesteros.codelabs.billboard.core.domain.model.Movie
import com.txusballesteros.codelabs.billboard.core.view.lifecycle.LifecycleView
import com.txusballesteros.codelabs.billboard.core.view.presentation.LifecyclePresenter
import com.txusballesteros.codelabs.billboard.feature.nowplaying.domain.usecase.GetNowPlayingMoviesUseCase
import com.txusballesteros.codelabs.billboard.navigation.NavigationCommand
import com.txusballesteros.codelabs.billboard.navigation.command.movieDetailNavigationCommand
import com.txusballesteros.codelabs.billboard.threading.perform

class NowPlayingPresenter(
    private val getNowPlayingMovies: GetNowPlayingMoviesUseCase
) : LifecyclePresenter<NowPlayingPresenter.View>() {

    override fun onViewAttached() {
        perform {
            getNowPlayingMovies()
                .onSuccess { movies -> view?.renderMovies(movies) }
                .onFailure { view?.renderError() }
        }
    }

    private suspend fun getNowPlayingMovies() = await {
        getNowPlayingMovies.execute()
    }

    fun onMovieTap(movie: Movie) {
        view?.navigateTo(movieDetailNavigationCommand(movie.id))
    }

    interface View : LifecycleView {
        fun renderMovies(movies: List<Movie>)
        fun renderError()
        fun navigateTo(command: NavigationCommand)
    }
}