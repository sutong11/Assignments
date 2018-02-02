from __future__ import print_function
#from models import build_vgg19
from models import build_lenet
#from models import build_vgg19_BN_afterReLU
import keras
from keras.preprocessing.image import ImageDataGenerator
from keras.optimizers import SGD
from keras.callbacks import EarlyStopping
from keras import backend as K
import cPickle
import glob
from PIL import Image
import random
import numpy as np
from sklearn.metrics import f1_score
from keras.models import load_model


batch_size = 32
num_classes = 1000
epochs = 20000 #200
data_augmentation = True
loadmodel = True
    
# input image dimensions
img_rows, img_cols = 256, 256

# The images are RGB.
img_channels = 3

'''
id_newlabel_train = cPickle.load(open('./ground_truth/train/id_newlabel_train.pkl', 'rb'))

# ---------- load validation set ----------#
# randomly take 10% of each class as validation set
# the smallest class only contains 7 images (label 5263)
class_imgIds = cPickle.load(open('./ground_truth/class_imgIds.pkl', 'rb'))

validation_set = []
for i in class_imgIds:
    n = len(class_imgIds[i])/10
    if n <= 1:   # if a class contains less than 10 images, only one image is taken out as validation
        tmp = random.sample(class_imgIds[i], 1)
        validation_set += tmp
    else:
        tmp = random.sample(class_imgIds[i], n)
        validation_set += tmp

val_num = len(validation_set)
x_validation = np.empty((val_num, 256, 256, 3))
y_validation = np.empty((val_num))

for i in xrange (val_num):
    img_id = validation_set[i]
    img = Image.open('./train/' + img_id + '.jpg')
    arr = np.asarray(img, dtype='float32')
    label = id_newlabel_train[int(img_id)]
    
    #----- mean subtraction ----#
    arr[:, :, 0] = (arr[:, :, 0] - np.mean(arr[:, :, 0])) #/ np.std(arr[:, :, 0])  # r channel
    arr[:, :, 1] = (arr[:, :, 1] - np.mean(arr[:, :, 1])) #/ np.std(arr[:, :, 1])  # g channel
    arr[:, :, 2] = (arr[:, :, 2] - np.mean(arr[:, :, 2])) #/ np.std(arr[:, :, 2])  # b channel
    #---------------------------#
    
    x_validation[i] = arr
    y_validation[i] = label

#x_validation[:, :, :, 0] = (x_validation[:,:,:,0] - np.mean(x_validation[:,:,:,0])) / np.std(x_validation[:,:,:,0])
#x_validation[:, :, :, 1] = (x_validation[:,:,:,1] - np.mean(x_validation[:,:,:,1])) / np.std(x_validation[:,:,:,1])
#x_validation[:, :, :, 2] = (x_validation[:,:,:,2] - np.mean(x_validation[:,:,:,2])) / np.std(x_validation[:,:,:,2])

y_validation = keras.utils.to_categorical(y_validation, 1000)
x_validation = x_validation.astype('float32')
x_validation /= 255.0

print ('---------- validation set done ----------')


# remove the images in the validation set from the training dataset
img_title = glob.glob('./train/*.jpg')
all_img = []
for img in img_title:
    img_id =img.split('/')[-1].split('.')[0]
    all_img.append(img_id)

training_set = set(all_img) - set(validation_set)

data_train = []  # a list of image titles such as ['103090-0.jpg', '103090-1.jpg', '103090-2.jpg']
for img in training_set:
    data_train.append(img+'-0.jpg')
    data_train.append(img+'-01.jpg')
    data_train.append(img+'-02.jpg')
    data_train.append(img+'-1.jpg')
    data_train.append(img+'-11.jpg')
    data_train.append(img+'-12.jpg')
    data_train.append(img+'-2.jpg')
    data_train.append(img+'-21.jpg')
    data_train.append(img+'-22.jpg')

random.shuffle(data_train)
total_num_train = len(data_train)
print ('---------- data_train done ----------')
'''

#--------load test data---------#
id_newlabel_test = cPickle.load(open('./ground_truth/test/id_newlabel_test.pkl', 'rb'))
total_num_test = cPickle.load(open('./ground_truth/test/total_test.pkl', 'rb'))

x_test = np.empty((total_num_test, 256, 256, 3))
y_test = np.empty((total_num_test))

data_test = glob.glob('./test/*.jpg')
for i in xrange (total_num_test):
    img_id = data_test[i].split('/')[2].split('.')[0]
    img = Image.open(data_test[i])
    arr = np.asarray(img, dtype='float32')
    label = id_newlabel_test.get(int(img_id))
    
    #----- mean subtraction ----#
    arr[:, :, 0] = (arr[:, :, 0] - np.mean(arr[:, :, 0])) #/ np.std(arr[:, :, 0])  # r channel
    arr[:, :, 1] = (arr[:, :, 1] - np.mean(arr[:, :, 1])) #/ np.std(arr[:, :, 1])  # g channel
    arr[:, :, 2] = (arr[:, :, 2] - np.mean(arr[:, :, 2])) #/ np.std(arr[:, :, 2])  # b channel
    #---------------------------#
    
    x_test[i] = arr
    y_test[i] = label

#x_test[:, :, :, 0] = (x_test[:,:,:,0] - np.mean(x_test[:,:,:,0])) / np.std(x_test[:, :, :, 0])
#x_test[:, :, :, 1] = (x_test[:,:,:,1] - np.mean(x_test[:,:,:,1])) / np.std(x_test[:, :, :, 1])
#x_test[:, :, :, 2] = (x_test[:,:,:,2] - np.mean(x_test[:,:,:,2])) / np.std(x_test[:, :, :, 2])

y_test_array = y_test
y_test= keras.utils.to_categorical(y_test, 1000)

x_test = x_test.astype('float32')
x_test /= 255.0

print ('---------- test data done ----------')


def generator(batch_size):
    #random.shuffle(data_train)
    
    # add a counter, if c == steps -> shuffle
    
    c = 0
    while True:
        
        x = np.empty((batch_size, 256, 256, 3))
        y = np.empty((batch_size))
        
        #batch = random.sample(data_train, batch_size)
        
        if c == total_num_train//batch_size:
            tmp = data_train[c*batch_size: ]
            batch = tmp + random.sample(data_train, (batch_size-len(tmp)))
            #batch = random.sample(data_train, batch_size)
        else:
            batch = data_train[c*batch_size : (c+1)*batch_size]
        
        for i in range (batch_size):
            img_id = batch[i].split('-')[0]
            img = Image.open('./trainX3/' + batch[i])

            arr = np.asarray(img, dtype='float32')
            label = id_newlabel_train.get(int(img_id))
            
            #----- mean subtraction ----#
            arr[:, :, 0] = arr[:, :, 0] - np.mean(arr[:, :, 0]) #/ np.std(arr[:, :, 0])  # r channel
            arr[:, :, 1] = arr[:, :, 1] - np.mean(arr[:, :, 1]) #/ np.std(arr[:, :, 1])  # g channel
            arr[:, :, 2] = arr[:, :, 2] - np.mean(arr[:, :, 2]) #/ np.std(arr[:, :, 2])  # b channel
            #---------------------------#
            
            x[i] = arr
            y[i] = label
        
        #x[:, :, :, 0] = (x[:,:,:,0] - np.mean(x[:,:,:,0])) / np.std(x[:, :, :, 0])
        #x[:, :, :, 1] = (x[:,:,:,1] - np.mean(x[:,:,:,1])) / np.std(x[:, :, :, 1])
        #x[:, :, :, 2] = (x[:,:,:,2] - np.mean(x[:,:,:,2])) / np.std(x[:, :, :, 2])

        y= keras.utils.to_categorical(y, 1000)
        
        x = x.astype('float32')
        x /= 255.0
        
        yield x, y  # if all the batches are trained, yield wont be performed, yet all the lines before yield function will sill be performed
      
      
        c += 1
        #print ('counter : ' + str(c))
        if c == (total_num_train//batch_size+1):
            c = 0
            random.shuffle(data_train)
            print ('***************************epoch done***************************')



def augmentation_generator(batch_size, datagen):
    # add a counter, if c == steps -> shuffle
    
    c = 0
    while True:
        
        x = np.empty((batch_size, 256, 256, 3))
        y = np.empty((batch_size))
        
        #batch = random.sample(data_train, batch_size)
        
        if c == total_num_train//batch_size:
            tmp = data_train[c*batch_size: ]
            batch = tmp + random.sample(data_train, (batch_size-len(tmp)))
        #batch = random.sample(data_train, batch_size)
        else:
            batch = data_train[c*batch_size : (c+1)*batch_size]
        
        for i in range (batch_size):
            img_id = batch[i].split('-')[0]
            img = Image.open('./trainX9/' + batch[i])
            
            arr = np.asarray(img, dtype='float32')
            label = id_newlabel_train.get(int(img_id))
            
            
            #----- mean subtraction ----#
            arr[:, :, 0] = arr[:, :, 0] - np.mean(arr[:, :, 0]) #/ np.std(arr[:, :, 0])  # r channel
            arr[:, :, 1] = arr[:, :, 1] - np.mean(arr[:, :, 1]) #/ np.std(arr[:, :, 1])  # g channel
            arr[:, :, 2] = arr[:, :, 2] - np.mean(arr[:, :, 2]) #/ np.std(arr[:, :, 2])  # b channel
            #---------------------------#
            
            x[i] = arr
            y[i] = label
        
        #x[:, :, :, 0] = (x[:,:,:,0] - np.mean(x[:,:,:,0])) / np.std(x[:, :, :, 0])
        #x[:, :, :, 1] = (x[:,:,:,1] - np.mean(x[:,:,:,1])) / np.std(x[:, :, :, 1])
        #x[:, :, :, 2] = (x[:,:,:,2] - np.mean(x[:,:,:,2])) / np.std(x[:, :, :, 2])

        y= keras.utils.to_categorical(y, 1000)
        
        x = x.astype('float32')
        x /= 255.0
        
        
        yield x, y  # if all the batches are trained, yield wont be performed, yet all the lines before yield function will sill be performed
        

        batch = datagen.flow(x, y, batch_size=batch_size)
        i = 0  # generate (batch_size*3) images
        for x_batch, y_batch in batch:
            yield x_batch, y_batch
            i += 1
            if i >=3:
                break
        
        c += 1
        if c == (total_num_train//batch_size+1):
            c = 0
            random.shuffle(data_train)
            print ('*************** epoch done ***************')


def train():
    
    model = build_lenet(img_rows, img_cols, num_classes)
    
    earlystop = EarlyStopping(monitor='val_loss',min_delta=0.00001, patience=5, verbose=1, mode='auto' )
    callbacks = [earlystop]
    
    # train the model using SGD/adam
    sgd = SGD(lr=0.0005, decay=1e-6, momentum=0.9, nesterov=True)
    model.compile(loss=keras.losses.categorical_crossentropy,
                  optimizer=sgd, #'adam',
                  metrics=['accuracy'])

    if not data_augmentation:
        print('Not using data augmentation.')
        model_info = model.fit_generator(generator(batch_size), steps_per_epoch=(total_num_train//batch_size+1), epochs=epochs,verbose=1, callbacks=callbacks, validation_data=(x_validation, y_validation))

    else:
        print('Using real-time data augmentation.')
        # This will do preprocessing and realtime data augmentation:
        datagen = ImageDataGenerator(featurewise_center=False,  # set input mean to 0 over the dataset
                                     samplewise_center=False,  # set each sample mean to 0
                                     featurewise_std_normalization=False,  # divide inputs by std of the dataset
                                     samplewise_std_normalization=False,  # divide each input by its std
                                     zca_whitening=False,  # apply ZCA whitening
                                     rotation_range=40,  # randomly rotate images in the range (degrees, 0 to 180)
                                     width_shift_range=0.2,  # randomly shift images horizontally (fraction of total width)
                                     height_shift_range=0.2,  # randomly shift images vertically (fraction of total height)
                                     shear_range=0.2,
                                     zoom_range=0.2,
                                     horizontal_flip=True,  # randomly flip images
                                     vertical_flip=True,    # randomly flip images
                                     fill_mode='nearest')
            
                                     # Compute quantities required for feature-wise normalization
                                     # (std, mean, and principal components if ZCA whitening is applied).
        #datagen.fit(x_train)
                                     
        # Fit the model on the batches generated by datagen.flow().
        model_info = model.fit_generator(augmentation_generator(batch_size, datagen),
                            steps_per_epoch=(total_num_train//batch_size+1)*4,
                            epochs=epochs,
                            verbose=1,
                            callbacks=callbacks,
                            validation_data=(x_validation, y_validation))
    
    model.save('./model_results/lenet_DA_GC_005.h5')
    model_history = model_info.history
    cPickle.dump(model_history, open('./History/lenet_DA_GC_005_history.pkl', 'wb'))
    
    return model



if __name__ == '__main__':
    print ('********** main start **********')
    
    if loadmodel:
        print ('load model')
        vgg = load_model('./model_results/vgg19.h5') #1
        print ('vgg done')
        pre_vgg = load_model('./model_results/vgg19_cw_pretrained.h5')  #2
        print ('pre vgg done')
        pre_vgg_da = load_model('./model_results/vgg19_pretrained_DA_real.h5')   #3
        print ('pre vgg da done')
        incep = load_model('./model_results/inception_pretrained.h5')   #4
        print ('incep done')

    else:
        # train the model
        print ('start training the model')
        model = train()
        print ('done with training')

    #score = model.evaluate(x_test, y_test, verbose=1)
    #print('Test loss:', score[0])
    #print('Test accuracy:', score[1])

    vgg_prob = vgg.predict(x_test)
#cPickle.dump(vgg_prob, open("vgg_prop.pkl", "wb"))

    pre_vgg_prob = pre_vgg.predict(x_test)
#   cPickle.dump(vgg_prob, open("pre_vgg_prop.pkl", "wb"))

    pre_vgg_da_prob = pre_vgg_da.predict(x_test)
#   cPickle.dump(vgg_prob, open("pre_vgg_da_prop.pkl", "wb"))

    incep_prob = incep.predict(x_test)
#   cPickle.dump(vgg_prob, open("incep_prob.pkl", "wb"))

    # ensemble1 of 3 4
    print ('ensemble of 3 4')
    ensemble1 = (pre_vgg_da_prob + incep_prob)/2.0
    y_pred = np.argmax(ensemble1, axis=1)

    f1_measure_micro = f1_score(y_test_array, y_pred, average='micro')
    f1_measure_macro = f1_score(y_test_array, y_pred, average='macro')
    f1_measure_weighted = f1_score(y_test_array, y_pred, average='weighted')
    print ('Test f1 measure mirco(= accuracy): ', f1_measure_micro)
    print ('Test f1 measure marco: ', f1_measure_macro)
    print ('Test f1 measure weighted: ', f1_measure_weighted)

    # ---------- competition metric ------------#
    user_dic = cPickle.load(open('./ground_truth/test/user_dic_test.pkl', 'rb'))
    sum_over_users = 0
    for author in user_dic:
        obs_avg = 0
        for observationid in user_dic[author]:
            obs_sum = 0
            for img_id in user_dic[author][observationid]:
                img = Image.open('./test/'+ img_id +'.jpg')
                arr = np.asarray(img, dtype='float32')
                arr = arr[np.newaxis, :, :, :]
                arr = arr.astype('float32')
                arr /= 255.0
                
                y_true = id_newlabel_test.get(int(img_id))
                #probabilities1 = vgg.predict(arr)
                #probabilities2 = pre_vgg.predict(arr)
                probabilities3 = pre_vgg_da.predict(arr)
                probabilities4 = incep.predict(arr)
                
                probabilities = (probabilities3 +probabilities4)/2.0

                ranks = [obj[0] for obj in sorted(zip(xrange(num_classes), probabilities[0]), key=lambda x:x[1], reverse=True)]
                
                true_rank = ranks.index(y_true)+1
                
                obs_sum += 1.0/true_rank
            
            obs_avg += (1.0/len(user_dic[author][observationid]))*obs_sum
                    
        sum_over_users += (1.0/len(user_dic[author]))*obs_avg

    ranking_score = (1.0/len(user_dic))*sum_over_users
    print ('competition ranking score: ', ranking_score)
    #---------------- done ----------------------#


    # ensemble2 of 3 3 4
    print ('******************************************************************')
    print ('ensemble2 of 2 3 4')
    ensemble2 = (pre_vgg_da_prob + pre_vgg_da_prob + incep_prob)/3.0
    y_pred = np.argmax(ensemble2, axis=1)
    
    f1_measure_micro = f1_score(y_test_array, y_pred, average='micro')
    f1_measure_macro = f1_score(y_test_array, y_pred, average='macro')
    f1_measure_weighted = f1_score(y_test_array, y_pred, average='weighted')
    print ('Test f1 measure mirco(= accuracy): ', f1_measure_micro)
    print ('Test f1 measure marco: ', f1_measure_macro)
    print ('Test f1 measure weighted: ', f1_measure_weighted)
    
    # ---------- competition metric ------------#
    user_dic = cPickle.load(open('./ground_truth/test/user_dic_test.pkl', 'rb'))
    sum_over_users = 0
    for author in user_dic:
        obs_avg = 0
        for observationid in user_dic[author]:
            obs_sum = 0
            for img_id in user_dic[author][observationid]:
                img = Image.open('./test/'+ img_id +'.jpg')
                arr = np.asarray(img, dtype='float32')
                arr = arr[np.newaxis, :, :, :]
                arr = arr.astype('float32')
                arr /= 255.0
                
                y_true = id_newlabel_test.get(int(img_id))
                probabilities2 = pre_vgg_da.predict(arr)
                probabilities3 = pre_vgg_da.predict(arr)
                probabilities4 = incep.predict(arr)
                
                probabilities = (probabilities2 +probabilities3 +probabilities4)/3.0
                
                ranks = [obj[0] for obj in sorted(zip(xrange(num_classes), probabilities[0]), key=lambda x:x[1], reverse=True)]
                
                true_rank = ranks.index(y_true)+1
                
                obs_sum += 1.0/true_rank
            
            obs_avg += (1.0/len(user_dic[author][observationid]))*obs_sum
        
        sum_over_users += (1.0/len(user_dic[author]))*obs_avg
    
    ranking_score = (1.0/len(user_dic))*sum_over_users
    print ('competition ranking score: ', ranking_score)
    #---------------- done ----------------------#

    # ensemble3 of 3 3 3 4
    print ('******************************************************************')
    print ('ensemble3 of 3 3 3 4')
    ensemble3 = (pre_vgg_da_prob + pre_vgg_da_prob + pre_vgg_da_prob + incep_prob)/4.0
    y_pred = np.argmax(ensemble3, axis=1)
    
    f1_measure_micro = f1_score(y_test_array, y_pred, average='micro')
    f1_measure_macro = f1_score(y_test_array, y_pred, average='macro')
    f1_measure_weighted = f1_score(y_test_array, y_pred, average='weighted')
    print ('Test f1 measure mirco(= accuracy): ', f1_measure_micro)
    print ('Test f1 measure marco: ', f1_measure_macro)
    print ('Test f1 measure weighted: ', f1_measure_weighted)
    
    # ---------- competition metric ------------#
    user_dic = cPickle.load(open('./ground_truth/test/user_dic_test.pkl', 'rb'))
    sum_over_users = 0
    for author in user_dic:
        obs_avg = 0
        for observationid in user_dic[author]:
            obs_sum = 0
            for img_id in user_dic[author][observationid]:
                img = Image.open('./test/'+ img_id +'.jpg')
                arr = np.asarray(img, dtype='float32')
                arr = arr[np.newaxis, :, :, :]
                arr = arr.astype('float32')
                arr /= 255.0
                
                y_true = id_newlabel_test.get(int(img_id))
                probabilities2 = pre_vgg_da.predict(arr)
                probabilities3 = pre_vgg_da.predict(arr)
                probabilities5 = pre_vgg_da.predict(arr)
                probabilities4 = incep.predict(arr)
                
                probabilities = (probabilities5 + probabilities2 +probabilities3 +probabilities4)/4.0
                
                ranks = [obj[0] for obj in sorted(zip(xrange(num_classes), probabilities[0]), key=lambda x:x[1], reverse=True)]
                
                true_rank = ranks.index(y_true)+1
                
                obs_sum += 1.0/true_rank
            
            obs_avg += (1.0/len(user_dic[author][observationid]))*obs_sum
        
        sum_over_users += (1.0/len(user_dic[author]))*obs_avg
    
    ranking_score = (1.0/len(user_dic))*sum_over_users
    print ('competition ranking score: ', ranking_score)
    #---------------- done ----------------------#

    # ensemble4 of 2 3 4 4
    print ('******************************************************************')
    print ('ensemble4 of 2 3 4 4')
    ensemble4 = (pre_vgg_prob + pre_vgg_da_prob + incep_prob + incep_prob)/4.0
    y_pred = np.argmax(ensemble4, axis=1)
    
    f1_measure_micro = f1_score(y_test_array, y_pred, average='micro')
    f1_measure_macro = f1_score(y_test_array, y_pred, average='macro')
    f1_measure_weighted = f1_score(y_test_array, y_pred, average='weighted')
    print ('Test f1 measure mirco(= accuracy): ', f1_measure_micro)
    print ('Test f1 measure marco: ', f1_measure_macro)
    print ('Test f1 measure weighted: ', f1_measure_weighted)
    
    # ---------- competition metric ------------#
    user_dic = cPickle.load(open('./ground_truth/test/user_dic_test.pkl', 'rb'))
    sum_over_users = 0
    for author in user_dic:
        obs_avg = 0
        for observationid in user_dic[author]:
            obs_sum = 0
            for img_id in user_dic[author][observationid]:
                img = Image.open('./test/'+ img_id +'.jpg')
                arr = np.asarray(img, dtype='float32')
                arr = arr[np.newaxis, :, :, :]
                arr = arr.astype('float32')
                arr /= 255.0
                
                y_true = id_newlabel_test.get(int(img_id))
                #probabilities1 = vgg.predict(arr)
                probabilities2 = pre_vgg.predict(arr)
                probabilities3 = pre_vgg_da.predict(arr)
                probabilities5 = incep.predict(arr)
                probabilities4 = incep.predict(arr)
                
                probabilities = (probabilities5 + probabilities2 +probabilities3 +probabilities4)/4.0
                
                ranks = [obj[0] for obj in sorted(zip(xrange(num_classes), probabilities[0]), key=lambda x:x[1], reverse=True)]
                
                true_rank = ranks.index(y_true)+1
                
                obs_sum += 1.0/true_rank
            
            obs_avg += (1.0/len(user_dic[author][observationid]))*obs_sum
        
        sum_over_users += (1.0/len(user_dic[author]))*obs_avg

    ranking_score = (1.0/len(user_dic))*sum_over_users
    print ('competition ranking score: ', ranking_score)
    #---------------- done ----------------------#

    # ensemble5 of 2 3 3 4 4 4
    print ('******************************************************************')
    print ('ensemble5 of 2 3 3 4 4 4')
    ensemble5 = (pre_vgg_prob + pre_vgg_da_prob + pre_vgg_da_prob + incep_prob + incep_prob + incep_prob)/6.0
    y_pred = np.argmax(ensemble5, axis=1)
    
    f1_measure_micro = f1_score(y_test_array, y_pred, average='micro')
    f1_measure_macro = f1_score(y_test_array, y_pred, average='macro')
    f1_measure_weighted = f1_score(y_test_array, y_pred, average='weighted')
    print ('Test f1 measure mirco(= accuracy): ', f1_measure_micro)
    print ('Test f1 measure marco: ', f1_measure_macro)
    print ('Test f1 measure weighted: ', f1_measure_weighted)
    
    # ---------- competition metric ------------#
    user_dic = cPickle.load(open('./ground_truth/test/user_dic_test.pkl', 'rb'))
    sum_over_users = 0
    for author in user_dic:
        obs_avg = 0
        for observationid in user_dic[author]:
            obs_sum = 0
            for img_id in user_dic[author][observationid]:
                img = Image.open('./test/'+ img_id +'.jpg')
                arr = np.asarray(img, dtype='float32')
                arr = arr[np.newaxis, :, :, :]
                arr = arr.astype('float32')
                arr /= 255.0
                
                y_true = id_newlabel_test.get(int(img_id))
                probabilities2 = pre_vgg.predict(arr)
                probabilities3 = pre_vgg_da.predict(arr)
                probabilities5 = pre_vgg_da.predict(arr)
                probabilities6 = incep.predict(arr)
                probabilities4 = incep.predict(arr)
                probabilities7 = incep.predict(arr)
                
                probabilities = (probabilities5 + probabilities2 +probabilities3 +probabilities4 + probabilities6 + probabilities7)/6.0
                
                ranks = [obj[0] for obj in sorted(zip(xrange(num_classes), probabilities[0]), key=lambda x:x[1], reverse=True)]
                
                true_rank = ranks.index(y_true)+1
                
                obs_sum += 1.0/true_rank
            
            obs_avg += (1.0/len(user_dic[author][observationid]))*obs_sum
        
        sum_over_users += (1.0/len(user_dic[author]))*obs_avg

    ranking_score = (1.0/len(user_dic))*sum_over_users
    print ('competition ranking score: ', ranking_score)
    #---------------- done ----------------------#

    print ('im doneeeeeee')


'''
predict_prob = model.predict(x_test)
    y_pred = np.argmax(predict_prob, axis=1)
    
    f1_measure_micro = f1_score(y_test_array, y_pred, average='micro')
    f1_measure_macro = f1_score(y_test_array, y_pred, average='macro')
    f1_measure_weighted = f1_score(y_test_array, y_pred, average='weighted')
    print ('Test f1 measure mirco(= accuracy): ', f1_measure_micro)
    print ('Test f1 measure marco: ', f1_measure_macro)
    print ('Test f1 measure weighted: ', f1_measure_weighted)

'''


