import BackgroundWrapper from "./BackgroundWrapper";
import React from "react";
import {Text, StyleSheet, View, Image} from "react-native";
import { TouchableOpacity } from "react-native-gesture-handler";


const SecondHomeScreen = ({navigation}) => {
    return (
        <BackgroundWrapper>
            <View style={styles.container}>
                <Image style={styles.image} source={require('../assets/images/mucus.png')}/>    
                <Text style={styles.text} >Snailmovieopedia</Text>
                <TouchableOpacity style={styles.button} onPress={() => navigation.navigate('Feed')}>
                    <Text style={styles.buttonText}>Welcome (back)!</Text>
                </TouchableOpacity>
            </View>
        </BackgroundWrapper>
    );
};

const styles = StyleSheet.create({
    container: {
        flex: 1,
    },
    image: {
        position: 'absolute',
        width: 800,
        height: 220,
        right: -300,
        top: 100,
        paddingRight: 15,
        resizeMode:'contain',
        overflow: 'hidden'
    },
    text: {
        position: 'absolute',
        width: 400,
        height: 100,
        left: 10,
        top: 275,
        fontSize: 40,
        lineHeight: 72,
        textAlign: 'center',
        color: '#fff',
        
    },
    button: {
        width: 200,
        height: 100,
        alignSelf: 'center',
        marginTop: 350,
        backgroundColor: '#34CB09',
        borderWidth: 5,
        borderColor: 'white',
        borderRadius: 15,
    },
    buttonText: {
        fontSize: 30,
        lineHeight: 36,
        textAlign: 'center',
        color: '#fff',
        marginTop: 7.5,
    }
});


export default SecondHomeScreen;
