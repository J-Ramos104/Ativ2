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

        //Configuração ao clicar no botão calcular
        binding.btnCalcular.setOnClickListener {
            //verificação se o campo está vazio
            if(binding.editTextText.text.toString().isEmpty()){
                //Se estiver vazio, é exibido uma mensagem avisando
                binding.textVwResultado.text = "Idade não informada. Por favor, preencha o campo"
                //Realiza a interrupção da execução
                return@setOnClickListener
            }
            //Lendo a idade digitada pelo usuário
            val idade = binding.editTextText.text.toString().toLong()
            //Pega o gênero selecionado no Spinner, pelo usuário
            val sexo = binding.spinner.selectedItem.toString()

            // Exibindo idade digitada (mensagem rápida)
            Toast.makeText(this, idade.toString(), Toast.LENGTH_SHORT).show()

            //Calculando o tempo para aposentadoria, conforme a idade
            val result = if (sexo == "Masculino") 65 - idade else 62 - idade

            //Exibindo a mensagem do resultado, dependendo do resultado
            binding.textVwResultado.text = if(result > 0) {
                "Faltam $result anos para você se aposentar"
            } else {
                "Você já tem direito à aposentadoria!"
            }

        }

    }
}