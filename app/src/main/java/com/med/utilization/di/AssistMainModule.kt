package com.med.utilization.di

import com.squareup.inject.assisted.dagger2.AssistedModule
import dagger.Module

@AssistedModule //annotate a Module with this annotation (btw you can also use other modules, even non-abstract ones)
@Module(includes = [AssistedInject_AssistMainModule::class])
abstract class AssistMainModule