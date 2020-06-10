package com.med.utilization.main

import com.squareup.inject.assisted.dagger2.AssistedModule
import dagger.Module

@AssistedModule //annotate a Module with this annotation (btw you can also use other modules, even non-abstract ones)
@Module(includes = [AssistedInject_MainViewModelModule::class])
abstract class MainViewModelModule