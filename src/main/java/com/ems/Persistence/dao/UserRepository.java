package com.ems.Persistence.dao;

import com.ems.Persistence.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);

    /*
    @Query("UPDATE Admin SET firstname = :firstname, lastname = :lastname, login = :login,
            superAdmin = :superAdmin, preferenceAdmin = :preferenceAdmin, address =  :address,
            zipCode = :zipCode, city = :city, country = :country, email = :email, profile = :profile,
            postLoginUrl = :postLoginUrl WHERE id = :id")

   public void update(@Param("firstname") String firstname, @Param("lastname") String lastname, @Param("login") String login,
            @Param("superAdmin") boolean superAdmin, @Param("preferenceAdmin") boolean preferenceAdmin,
            @Param("address") String address, @Param("zipCode") String zipCode, @Param("city") String city,
            @Param("country") String country, @Param("email") String email, @Param("profile") String profile,
            @Param("postLoginUrl") String postLoginUrl, @Param("id") Long id);

    @Modifying(clearAutomatically = true)
    @Query("update RssFeedEntry feedEntry set feedEntry.read =:isRead where feedEntry.id =:entryId")
    void markEntryAsRead(@Param("entryId") Long rssFeedEntryId, @Param("isRead") boolean isRead);


    @Modifying
    @Query("Update Todo t SET t.title=:title WHERE t.id=:id")
    public void updateTitle(@Param("id") Long id, @Param("title") String title);

    @NamedQueries({
    @NamedQuery(name="allStudentRecords",query="SELECT st FROM Student st WHERE st.sroll > ?1"),
    @NamedQuery(name="updateStudentRecords", query="UPDATE Student st SET st.sname= ?1 WHERE st.sname= ?2")
})

 */

    @Modifying
    @Transactional
    @Query("update User u set u.password = :password where u.id = :user_id")
    void updatePassword(@Param("password") String password, @Param("user_id") int user_id);
}
