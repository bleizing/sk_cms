/**
 * 
 */

var Crypto={
    IV:'0000000000000000',
    CIPHER_NAME:'AES-ECB',
	    
	encrypt:function(message,password){
        var cipher=forge.cipher.createCipher(Crypto.CIPHER_NAME, password);
        cipher.start({iv:Crypto.IV});
        cipher.update(forge.util.createBuffer(message));
        cipher.finish();
        var cipherText = forge.util.encode64(cipher.output.getBytes());
        return cipherText;
    },

    decrypt:function(cipherText, password) {
        var decipher = forge.cipher.createDecipher(Crypto.CIPHER_NAME, password);
        decipher.start({iv: forge.util.decode64(Crypto.IV)});
        decipher.update(forge.util.createBuffer(forge.util.decode64(cipherText)));
        decipher.finish();
        return decipher.output.toString();
    }
};