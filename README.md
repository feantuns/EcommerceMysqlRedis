Documentação

O sistema foi desenvolvido seguindo o exemplo do exercício 05 apresentado em aula, com Spring Data JPA + Cache Redis. Essas tecnologias foram escolhidas porque não tenho muita experiência com as demais e como estou começando no mundo Java decidi seguir na opção mais simples para conseguir absorver mais os conceitos básicos.

Quanto as entidades, foram necessárias 3: Cliente, Produto e Pedido; também foi necessário uma tabela intermediária para a relação entre pedidos e produtos, já que um pedido pode ter n produtos e um produto pode estar em n pedidos. 

Por outro lado, a entidade Cliente tem uma relação um para n com a entidade Pedido, já que um Cliente pode ter vários pedidos mas um pedido só pode ter um cliente. Para isso foi utilizado a annotation @OneToMany na classe Cliente para listar todos os pedidos dele. E para registrar o cliente do pedido foi utilizado a annotation @ManyToOne na entidade Pedido.

![image](https://user-images.githubusercontent.com/33201697/215269333-aa141bd0-c581-4f63-88b8-e418baec75cd.png)

MER

Em relação à estrutura do projeto, foram criadas as 3 entidades e seus respectivos Controllers e Services. Todos os métodos CRUD foram criados, seguindo o padrão do exemplo.

O fluxo de uso é o seguinte:

1. Cadastra-se um cliente em /api/loja/cliente
2. Cadastra-se um produto em /api/loja/produto
3. Cadastra-se um pedido em /api/loja/cliente/{idCliente}/pedido
4. Adiciona-se produtos em um pedido em /api/loja/pedido/{idPedido}/produto
