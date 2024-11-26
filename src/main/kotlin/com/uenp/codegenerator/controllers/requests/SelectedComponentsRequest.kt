package com.uenp.codegenerator.controllers.requests

import com.uenp.codegenerator.domain.Stages
import com.uenp.codegenerator.domain.Vowels

data class SelectedComponentsRequest(
    val configurations: ConfigurationsRequest,
    val components: ComponentsRequest?,
    val scope: ScopeRequest?
)

data class ConfigurationsRequest(
    val projectName: String,
    val gitIgnore: Boolean,
    val database: Boolean,
    val baseSounds: Boolean
)

data class ComponentsRequest(
    val menu: Boolean,
    val card: Boolean?,
    val buttons: Boolean?,
    val scoreAndTime: Boolean,
    val intro: Boolean,
    val startScreen: Boolean,
    val endScreen: Boolean
)

data class ScopeRequest(
    val vowels: List<Vowels>?,
    val stages: List<Stages>?
)