package com.rest.restex.repository;

import com.rest.restex.one.CustomberModel;
import io.micrometer.observation.ObservationFilter;
import org.springframework.data.repository.CrudRepository;
public interface CustomerRepo extends CrudRepository<CustomberModel, Integer> {

}
