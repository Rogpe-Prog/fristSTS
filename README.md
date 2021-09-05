# Primeiro projeto Java web no Spring Boot
## --- Inspirado by Prof. Nelio Alves ---

# Sumario

1. Pre-requisitos
2. Links sobre REST
3. Passos
4. Diagramas
5. O que voce vai aprender
6. Criar um simples projeto Java web no Spring Boot e Maven
7. Introducao prática a injecao de dependencia no Spring Boot
8. Introducao pratica a REST / web services
9. Introducao pratica ao Spring Data JPA com banco H2

# Pre-requisitos
* Logica de programacao
* OO básica
* Composicao de objetos (veja: https://github.com/devsuperior/aulao004)
* BD basico

# Passos

* Criar projeto Maven usando Spring Initializr e importar no STS

# Sugestao: acrescentar no .gitignore:

* .DS_Store
* .vscode
* .metadata
* .mvn

* mvnw
* mvnw.cmd

# Criar a entidade Category

## Criar CategoryResource

```
@RestController
@RequestMapping(value = "/categories")
public class CategoryResource {

	@GetMapping
	public ResponseEntity<...> findAll() {
		...
		return ResponseEntity.ok().body(...);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<...> findById(@PathVariable Long id) {
		...
		return ResponseEntity.ok().body(...);
	}
}
```

# Criar CategoryRepository
```
@Component
public class CategoryRepository {

	public void save(Category obj) {
		...
	}

	public Category findById(Long id) {
		...
	}
	
	public List<Category> findAll() {
		...
	}
}
```

# Implementar CommandLineRunner para instanciar categorias no startup da aplicacao. Usar o mecanismo de injecao de dependencia do Spring Boot para obter uma instancia de CategoryRepository.

```
Category cat1 = new Category(1L, "Electronics");
Category cat2 = new Category(2L, "Books");
Opcional: salvar um commit
```

# Acrescentar entidade Product ao projeto

```
Category cat1 = new Category(1L, "Electronics");
Category cat2 = new Category(2L, "Books");

Product p1 = new Product(1L, "TV", 2200.00, cat1);
Product p2 = new Product(2L, "Domain Driven Design", 120.00, cat2);
Product p3 = new Product(3L, "PS5", 2800.00, cat1);
Product p4 = new Product(4L, "Docker", 100.00, cat2);

cat1.getProducts().addAll(Arrays.asList(p1, p3));
cat2.getProducts().addAll(Arrays.asList(p2, p4));
```

# Acrescentar as dependencias Maven de Spring Data JPA e banco H2

```
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>

<dependency>
	<groupId>com.h2database</groupId>
	<artifactId>h2</artifactId>
	<scope>runtime</scope>
</dependency>
```

# Acrescentar as configuracoes do H2 em application.properties

```
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.username=sa
spring.datasource.password=

spring.h2.console.enabled=true
spring.h2.console.path=/h2-console

spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```

# Fazer os mapeamentos objeto-relacional

```
@Entity
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private Double price;
	
	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;
```

# Retirar os ID's das instancias em CommandLineRunner (deixar como null)

# Trocar o codigo dos repositories: usar JpaRepository<Entity, ID>


# Diagramas

## Modelo conceitual
![Modelo conceitual](https://github.com/devsuperior/aulao005/raw/master/domain-model.png)

## Instancia
![Instancia](https://github.com/devsuperior/aulao005/raw/master/domain-instance.png)