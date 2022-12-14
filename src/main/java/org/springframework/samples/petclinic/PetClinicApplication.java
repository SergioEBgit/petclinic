/*
 * Copyright 2012-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.samples.petclinic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.samples.petclinic.vet.Specialty;
import org.springframework.samples.petclinic.vet.SpecialtyRepository;
import org.springframework.samples.petclinic.vet.Vet;
import org.springframework.samples.petclinic.vet.VetRepository;

/**
 * PetClinic Spring Boot Application.
 *
 * @author Dave Syer
 *
 */
@SpringBootApplication
public class PetClinicApplication {

	Logger log = LoggerFactory.getLogger(PetClinicApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(PetClinicApplication.class, args);
	}

	@Bean
	public CommandLineRunner demoVetRepository(VetRepository vetRepository, SpecialtyRepository specialtyRepository) {
		return (args) -> {
			log.info("*****************************************************");
			log.info("BOOTCAMP - Spring y Spring Data - vetRepository");
			log.info("*****************************************************");
			log.info("Creamos un objeto Vet");
			Vet vet = new Vet();
			vet.setFirstName("Sergio");
			vet.setLastName("Raposo Vargas");
			log.info("Persistimos en BBDD");
			vet = vetRepository.save(vet);
			log.info("Comprobamos que se ha creado correctamente");
			Vet vetAux = vetRepository.findOne(vet.getId());
			log.info(vetAux.toString());
			log.info("Editamos el objeto y a??adimos una Speciality");
			Specialty s = specialtyRepository.findOne(1);
			vet.addSpecialty(s);
			vet = vetRepository.save(vet);
			log.info(vet.toString());
			log.info("Listamos todos los veterinarios");
			for (Vet v : vetRepository.findAll()) {
				log.info("Vet: " + v);
			}
			log.info("Listamos todos los radi??logos");
			Pageable firstPage = PageRequest.of(0, 10);
			for (Vet v : vetRepository.findRadiologists(firstPage).getContent())
				log.info(v.toString());

			log.info("FindBySpecialtyName");
			for (Vet v : vetRepository.findBySpecialtyName("radiology"))
				log.info(v.toString());
		};
	}

}
