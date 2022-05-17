package com.example.tacocloud.model;

// tag::all[]
// tag::allButValidation[]
import java.util.Date;
import java.util.List;
// end::allButValidation[]
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
// tag::allButValidation[]
import lombok.Data;

@Data
public class Taco {
    private Long id;
    // end::allButValidation[]
    @NotNull
    @Size(min=5, message="Name must be at least 5 characters long")
    // tag::allButValidation[]
    private String name;
    // end::allButValidation[]
    private Date createdAt;
    @Size(min=1, message="You must choose at least 1 ingredient")
    // tag::allButValidation[]
    private List<Ingredient> ingredients;

}
//end::allButValidation[]
//tag::end[]