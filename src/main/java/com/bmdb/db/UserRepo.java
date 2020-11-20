package com.bmdb.db;

import org.springframework.data.jpa.repository.JpaRepository;
import com.bmdb.business.*;

public interface UserRepo extends JpaRepository<User, Integer> {

}
