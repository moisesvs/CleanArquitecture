/**
 * Copyright (C) 2015 Moisés Vázquez Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.projects.moi.ca.domain;

/**
 * News used in the data layer.
 */
public class News {

    /**
     * The title entity
     */
    private String title;

    /**
     * The description news entity
     */
    private String description;

    /**
     * Default news entity
     */
    public News() {
        //empty
    }

    /**
     * Constructoru news entity
     */
    public News(String title, String description) {
        this.title = title;
        this.description = description;
    }

    /**
     * Get title
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Set title
     * @param title the title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Get description
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set description
     * @param description the description
     */
    public void setDescription(String description) {
        this.description = description;
    }
}
