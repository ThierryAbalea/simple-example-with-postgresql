package com.qovery.example

import com.qovery.client.Qovery
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.ClassPathResource
import java.io.File
import javax.sql.DataSource


/**
 * Created by evoxmusic on 20/12/2019.
 */
@Configuration
class MyPostgresqlConfiguration {

    @Bean
    fun getDataSource(): DataSource? {
        val f = ClassPathResource(".qovery${File.separator}local_configuration.json").file

        val databaseConfiguration = Qovery(f).getDatabaseConfiguration("my-postgresql")
        val host = databaseConfiguration.host
        val port = databaseConfiguration.port
        val username = databaseConfiguration.username
        val password = databaseConfiguration.password

        return DataSourceBuilder.create()
                .driverClassName("org.postgresql.Driver")
                .url("jdbc:postgresql://$host:$port/postgres")
                .username(username)
                .password(password)
                .build()
    }

}
