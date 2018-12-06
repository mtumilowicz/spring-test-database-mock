[![Build Status](https://travis-ci.com/mtumilowicz/spring-test-database-mock.svg?branch=master)](https://travis-ci.com/mtumilowicz/spring-test-database-mock)

# spring-test-database-mock
It is often better to provide in memory repository than mocking / h2 during tests.

_Reference_: https://www.youtube.com/watch?v=5Q8kiSN6390

# project description
Instead of mocking / h2 in tests it is often better to provide in memory mock repositories:

1. suppose we have a customer service:
    ```
    @Service
    @FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
    @RequiredArgsConstructor
    class CustomerService {
        CustomerRepository customerRepository;
    
        Customer save(Customer customer) {
            return customerRepository.save(customer);
        }
    
        Optional<Customer> findById(Integer id) {
            return customerRepository.findById(id);
        }
        
        void deleteById(Integer id) {
            customerRepository.deleteById(id);
        }
        
        List<Customer> findAll() {
            return customerRepository.findAll();
        }
    }
    ```
    **remark**: we use only 4 method from `CustomerRepository`:
    * `save`
    * `findById`
    * `deleteById`
    * `findAll`
1. and we have a `CustomerRepository` (simple extension of spring `CrudRepository`)
    ```
    @Repository
    interface CustomerRepository extends CrudRepository<Customer, Integer> {
        @Override
        List<Customer> findAll();
    }
    ```
1. we want to test `CustomerService` so instead of mocking `CustomerRepository`
method we simply provide `InMemoryCustomerRepository` implementation:
    ```
    public class InMemoryCustomerRepository implements CustomerRepository {
        
        private final ConcurrentHashMap<Integer, Customer> map = new ConcurrentHashMap<>();
        
        @Override
        public <S extends Customer> S save(S entity) {
            map.put(entity.getId(), entity);
            return entity;
        }
    
        @Override
        public Optional<Customer> findById(Integer integer) {
            return Optional.ofNullable(map.get(integer));
        }
    
        @Override
        public List<Customer> findAll() {
            return new LinkedList<>(map.values());
        }
    
        @Override
        public void deleteById(Integer integer) {
            map.remove(integer);
        }
        
        ... other methods
    }
    ```
    and we pass it to `CustomerService` constructor in tests, for example
    ```
    def "save - findById"() {
            given:
            def customerService = new CustomerService(new InMemoryCustomerRepository())
    
            and:
            def customer = Customer.builder().id(1).name("name").build()
    
            when:
            customerService.save(customer)
            
            then:
            customerService.findById(1) == Optional.of(customer)
    }
    ```