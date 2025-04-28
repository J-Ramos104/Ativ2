package com.juliarld.ativaposentadoria

import android.os.Bundle
import android.widget.Toast
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.juliarld.ativaposentadoria.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    //Declarando a variavel de binding (ViewBinding)
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Inicialização do Binding com layout da activity
        binding= ActivityMainBinding.inflate(layoutInflater)
        //Define a vizualização do bindinding.root, no qual representa o layout raiz do arquivo XML
        setContentView(binding.root)

        //Criando uma lista de opções(genero) para o Spinner
        val genero = listOf("Masculino","Feminino")

        //Configurações do adapter
        val adapter = ArrayAdapter(this,android.R.layout.simple_spinner_item,genero)
        //Definindo o layout do item dropdown do Spinner
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        //Associando o adapter ao spineer
        binding.spinner.adapter = adapter

        // Configurando o ícone no TextView do nome
        binding.textVwNome.setCompoundDrawablesWithIntrinsicBounds(
            R.drawable.baseline_person_24,0,0,0
        )

        //Configuração ao clicar no botão calcular
        binding.btnCalcular.setOnClickListener {

            //Lendo a idade digitada pelo usuário
            val idade = binding.editTextText.text.toString().toLongOrNull()
            //Pega o gênero selecionado no Spinner, pelo usuário
            val sexo = binding.spinner.selectedItem as String

            //Verificando se o campo para preencher a idade está vazia
            if (idade != null) {
                var resultado: Long

                // Exibindo idade digitada (mensagem rápida)
                Toast.makeText(this,"Idade digitada: $idade", Toast.LENGTH_SHORT).show()

                //Verificando o sexo selecionado
                if(sexo.trim() == "Masculino") {
                    resultado = 65 - idade
                } else {
                    resultado = 62 - idade
                }
                //Exibindo a mensagem do resultado, dependendo do resultado
                if (resultado > 0) {
                    binding.textVwResultado.text = "Faltam $resultado anos para você se aposentar"

                    //Configurando o icon para que apareça junto com a mensagem
                    binding.textVwResultado.setCompoundDrawablesWithIntrinsicBounds(
                        R.drawable.baseline_hourglass_top_24,0,0,0
                    )

                } else{
                    binding.textVwResultado.text = "Você já tem direito à aposentadoria"

                    //Configurando o icon para que apareça junto com a mensagem
                    binding.textVwResultado.setCompoundDrawablesWithIntrinsicBounds(
                        R.drawable.baseline_stars_24,0,0,0
                    )
                }
            } else{
                //Mostrando uma mensagem avisando erro caso o campo de digitar a idade esteja vazio
                binding.textVwResultado.text = "Informe sua idade, por gentileza."

                //Configurando o icon para que apareça junto com a mensagem
                binding.textVwResultado.setCompoundDrawablesWithIntrinsicBounds(
                    R.drawable.baseline_info_24,0,0,0
                )
            }
        }

    }
}