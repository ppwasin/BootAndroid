package com.med.feature.entries.di

import com.squareup.inject.assisted.dagger2.AssistedModule
import dagger.Module

@AssistedModule //annotate a Module with this annotation (btw you can also use other modules, even non-abstract ones)
@Module(includes = [AssistedInject_EntriesViewModelModule::class])
abstract class EntriesViewModelModule


//Step to create new AssistedModule
//1)
//@AssistedModule
//@Module
//abstract class ConversationViewModelModule

//hit build
//this will generate for us: AssistedInject_ConversationViewModelModule.
//just include it to any module, in our case, ViewModelModule:

//2)
//@AssistedModule //annotate a Module with this annotation (btw you can also use other modules, even non-abstract ones)
//@Module(includes=[AssistedInject_ConversationViewModelModule::class])
//abstract class ConversationViewModelModule

//3)
//include into Component
//@Component(
//	dependencies = [AppComponent::class],
//	modules = [ConversationViewModelModule::class]
//)