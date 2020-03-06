package com.med.utilization

import com.med.dynamicfeature.installer.FeatureInstallSyntax

interface AppDiProvider {
	val featureSyntax: FeatureInstallSyntax
}