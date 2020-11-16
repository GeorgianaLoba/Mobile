import 'react-native-gesture-handler';
/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 *
 * @format
 * @flow strict-local
 */

import React from 'react';
import {createStackNavigator} from "@react-navigation/stack";
import {NavigationContainer} from "@react-navigation/native";
import HomeScreen from "./snailmovieopedia/HomeScreen";
import SecondHomeScreen from "./snailmovieopedia/SecondHomeScreen";
import FeedScreen from './snailmovieopedia/FeedScreen';
import AddScreen from './snailmovieopedia/AddScreen';
import EditScreen from './snailmovieopedia/EditScreen';

import {
  StyleSheet,
} from 'react-native';

const Stack = createStackNavigator();

const App = () => {
  return (
    <NavigationContainer>
     <Stack.Navigator>
        <Stack.Screen name={"Home"} component={HomeScreen}/>
        <Stack.Screen name={"SecondHome"} component={SecondHomeScreen}/>
        <Stack.Screen name={"Feed"} component={FeedScreen}/>
        <Stack.Screen name={"Add"} component={AddScreen}/>
        <Stack.Screen name={"Edit"} component={EditScreen}/>

      </Stack.Navigator>
    </NavigationContainer>
  );
};


const styles = StyleSheet.create({
  nav: {
      display: 'none',
  }
});


export default App;
