package com.projects.moi.ca.data.entity.mapper;

/**
 * Class used to transform from Strings representing json to valid objects.
 */
public class NewsEntityJsonMapper {

//    private final Gson gson;

    public NewsEntityJsonMapper() {
//        this.gson = new Gson();
    }

//    /**
//     * Transform from valid json string to {@link NewsEntity}.
//     *
//     * @param userJsonResponse A json representing a user profile.
//     * @return {@link NewsEntity}.
//     * @throws com.google.gson.JsonSyntaxException if the json string is not a valid json structure.
//     */
//    public NewsEntity transformUserEntity(String userJsonResponse) throws JsonSyntaxException {
//        try {
//            Type userEntityType = new TypeToken<NewsEntity>() {}.getType();
//            NewsEntity userEntity = this.gson.fromJson(userJsonResponse, userEntityType);
//
//            return userEntity;
//        } catch (JsonSyntaxException jsonException) {
//            throw jsonException;
//        }
//    }
//
//    /**
//     * Transform from valid json string to List of {@link UserEntity}.
//     *
//     * @param userListJsonResponse A json representing a collection of users.
//     * @return List of {@link UserEntity}.
//     * @throws com.google.gson.JsonSyntaxException if the json string is not a valid json structure.
//     */
//    public List<NewsEntity> transformUserEntityCollection(String userListJsonResponse)
//            throws JsonSyntaxException {
//
//        List<NewsEntity> userEntityCollection;
//        try {
//            Type listOfUserEntityType = new TypeToken<List<NewsEntity>>() {}.getType();
//            userEntityCollection = this.gson.fromJson(userListJsonResponse, listOfUserEntityType);
//
//            return userEntityCollection;
//        } catch (JsonSyntaxException jsonException) {
//            throw jsonException;
//        }
//    }
}