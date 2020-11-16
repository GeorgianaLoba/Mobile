import React from 'react';
import BackgroundWrapper from "./BackgroundWrapper";
import {Image, View, StyleSheet, Button} from "react-native";

const HomeScreen = ({navigation}) => {
    return (
        <BackgroundWrapper>
            <View style={styles.container} onStartShouldSetResponder={() => navigation.navigate('SecondHome')}>
                <Image style={styles.snail} source={require('../assets/images/snail.png')}/>
                <Image style={styles.popcorn} source={require('../assets/images/popcorn.png')}/>
            </View>
        </BackgroundWrapper>
    );
};

const styles = StyleSheet.create({
    container: {
        flex: 1,
    },
    snail: {
        width: 214,
        height: 271,
        position: 'absolute',
        top: 100,
        left: 150,
        resizeMode:'contain',
    },
    popcorn: {
        position: 'absolute',
        width: 257.21,
        height: 189.37,
        left: 5,
        top: 235,
        transform: [{ rotate: '-10deg'}],
        resizeMode:'contain',
    }
});

export default HomeScreen;
