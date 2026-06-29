

//@Entity
public class Role {

//    @Id
//    @GeneratedValue
    private Long id;

    private String name;

//    @ManyToMany
//    @JoinTable(
//        name = "role_authority",
//        joinColumns = @JoinColumn(name = "role_id"),
//        inverseJoinColumns = @JoinColumn(name = "authority_id")
//    )
    private List<Authority> authorities;
}