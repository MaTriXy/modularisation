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
package com.txusballesteros.codelabs.billboard.feature.nowplaying.di

import com.txusballesteros.codelabs.billboard.feature.nowplaying.data.datasource.NowPlayingCloudDataSource
import com.txusballesteros.codelabs.billboard.feature.nowplaying.data.datasource.NowPlayingDataSource
import com.txusballesteros.codelabs.billboard.feature.nowplaying.data.repository.NowPlayingRepository
import com.txusballesteros.codelabs.billboard.feature.nowplaying.domain.usecase.GetNowPlayingMoviesUseCase
import com.txusballesteros.codelabs.billboard.feature.nowplaying.presentation.NowPlayingPresenter
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

internal val featureModule = Kodein.Module(name = "NowPlayingFeatureModule") {
    bind<NowPlayingDataSource>() with provider {
        NowPlayingCloudDataSource(
            instance()
        )
    }
    bind<NowPlayingRepository>() with singleton {
        NowPlayingRepository(
            instance()
        )
    }
    bind<NowPlayingPresenter>() with provider { NowPlayingPresenter(instance()) }
    bind<GetNowPlayingMoviesUseCase>() with provider { GetNowPlayingMoviesUseCase(instance()) }
}
