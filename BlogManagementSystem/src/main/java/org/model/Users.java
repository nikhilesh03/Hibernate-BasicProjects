    package org.model;

    import jakarta.persistence.*;

    import java.sql.Timestamp;
    import java.util.Date;
    import java.util.List;

    @Entity
    public class Users {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;
        private String name;
        private String email;
        private String address;
        private Timestamp created_at;
        @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
        private List<Blog> blog;

        public List<Blog> getBlog() {
            return blog;
        }

        public void setBlog(List<Blog> blog) {
            this.blog = blog;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public Timestamp getCreated_at() {
            return created_at;
        }

        public void setCreated_at(Timestamp created_at) {
            this.created_at = created_at;
        }

        @Override
        public String toString() {
            return "Users{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", email='" + email + '\'' +
                    ", address='" + address + '\'' +
                    ", created_at=" + created_at +
                    ", blog=" + blog +
                    '}';
        }
    }
