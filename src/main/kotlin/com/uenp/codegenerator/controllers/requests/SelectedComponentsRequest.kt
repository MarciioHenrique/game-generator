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
        val screenDimensions: String?,
        val screenProportions: String?,
        val gitIgnore: Boolean
)

data class ComponentsRequest(
        val menu: Boolean,
        val card: Boolean?,
        val buttons: Boolean?,
        val scoreAndTime: Boolean?
)

data class ScopeRequest(
        val vowels: List<Vowels>?,
        val stages: List<Stages>?
)