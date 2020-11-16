import React from 'react';
import {
    Image,
    StyleSheet, 
    View,
    Text,
} from 'react-native';

const TopBar = ({ text }) => {
    return(
        <View style={styles.container}>
            <Image style={styles.icon} source={require('../assets/images/topicon.png')}/>
            <View style={styles.banner}>
                <Text style={styles.txt}>{text}</Text>
            </View>
        </View>
    );
};

const styles = StyleSheet.create({
    container: {
        position: 'absolute',
        top: 0,
        left: 0,
        width: 500,
        height:80,
        overflow: "hidden",
        display: 'flex',
        flexDirection: 'row',
        justifyContent: 'space-between',
        //flexWrap: 'wrap',
    },
    icon: {
        position: 'absolute',
        width: 80,
        height: 70,
        left: 0,
        margin: 3,
    },
    banner: {
        position: 'absolute',
        backgroundColor: '#000000',
        borderWidth: 2,
        width: 300,
        height: 50,
        left: 90,
        top: 15,
        overflow: 'hidden',
        borderColor: '#90FF00',
    },
    txt: {
        position: 'absolute',
        fontSize: 25,
        lineHeight: 35,
        left: 40,
        textAlign: 'center',
        color: '#B9CB53'
    }
});

export default TopBar;
