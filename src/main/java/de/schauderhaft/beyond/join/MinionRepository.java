/*
 * Copyright 2021 the original author or authors.
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
package de.schauderhaft.beyond.join;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MinionRepository extends CrudRepository<Minion, Long> {

	@Query("select m.id as id, m.name name, m.number_of_eyes as number_of_eyes, " +
			"p.name as master_name, p.id as master_evil_master " +
			"from minion m join person p on p.id = m.evil_master")
	List<MinionView> allMinionViews();
}
