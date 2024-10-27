package com.uenp.codegenerator.application.components.configurations

import com.uenp.codegenerator.application.components.interfaces.ConfigurationComponent
import com.uenp.codegenerator.controllers.requests.ConfigurationsRequest

class Database : ConfigurationComponent {
    override fun generateScript(configurations: ConfigurationsRequest): String {
        return """
            extends Node

            var words: Dictionary
            var array_of_words: Array
            var array_of_images: Array

            var syllable_length
            var word_without_syllable

            var number = RandomNumberGenerator.new()
            var num

            func _ready():
            	# para procurar aleatoriamente
            	randomize()
            	
            	# Carregar o JSON e passar para a variável
            	words = load_json()
            	array_of_words = words["words"]
            	
            	# Embaralhar array 
            	array_of_words.shuffle()
            	
            	# Carregamento de imagem
            	array_of_images = array_of_words[0]["images"]
            	array_of_images.shuffle()


            func shuffle_syllables():
            	array_of_words.shuffle()
            	#carregamento de imagem
            	array_of_images = array_of_words[0].images
            	# variaveis para retirar a silaba da palavra
            	syllable_length = array_of_words[0].syllable.length()
            	word_without_syllable = array_of_words[0].word.substr(syllable_length,-1)


            func raffle_image():
            	num = number_roulette()
            	return num.randi_range(0,2)


            func number_roulette():
            	number.randomize()
            	return number


            func load_json():
            	var file = "res://import/words.json"
            	var json_as_text = FileAccess.get_file_as_string(file)
            	var json_as_dict = JSON.parse_string(json_as_text)
            	return json_as_dict

        """.trimIndent()
    }
}