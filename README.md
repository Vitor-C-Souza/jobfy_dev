# Jobify Dev - Encontre sua vaga na tecnologia

Jobify Dev é um aplicativo Android moderno para busca de vagas remotas na área de tecnologia, desenvolvido com as melhores práticas de desenvolvimento Android, incluindo **Jetpack Compose**, **Clean Architecture** e **Material Design 3**.

## 📱 Showcase

Aqui estão as telas do projeto baseadas no design original:

![Showcase 1](screenshots/showcase1.png)
![Showcase 2](screenshots/showcase2.png)

## 🚀 Tecnologias e Bibliotecas

O projeto utiliza as tecnologias mais recentes do ecossistema Android:

- **Jetpack Compose:** UI declarativa e moderna.
- **Clean Architecture:** Organização do código em camadas (Data, Domain, UI) com foco em UseCases para regras de negócio.
- **Hilt:** Injeção de dependência para facilitar a testabilidade e manutenção.
- **Retrofit & OkHttp:** Consumo de APIs REST (Remotive API).
- **Room:** Banco de dados local para persistência das vagas favoritas.
- **Navigation Compose:** Navegação fluida entre telas.
- **Coroutines & Flow:** Gerenciamento de tarefas assíncronas e fluxo de dados reativo.
- **Coil:** Carregamento de imagens eficiente.

## 🏗️ Arquitetura

O projeto segue os princípios da **Clean Architecture**, garantindo que as regras de negócio sejam independentes de detalhes de implementação como frameworks de UI ou banco de dados:

- **Domain Layer:** Contém os Modelos, Repositórios (interfaces) e **UseCases** (regras de negócio).
- **Data Layer:** Implementação dos Repositórios, Fontes de Dados (Remote e Local/Room) e Mapeadores (Mappers).
- **UI Layer:** ViewModels (gerenciamento de estado) e Composables (visualização).

## ✨ Funcionalidades

- Listagem de vagas de tecnologia em tempo real.
- Filtro por categorias e busca por termos específicos.
- Detalhes completos da vaga (salário, localização, stack tecnológica).
- Sistema de favoritos (salvamento offline com Room).
- Compartilhamento de vagas.
- Navegação direta para o site da vaga para aplicação.

---
Desenvolvido por **Vitor C. Souza** 🚀
