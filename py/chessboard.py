import chess
import chess.variant
from tensorflow.keras import models
from tensorflow.keras.optimizers import SGD
from tensorflow.keras.layers import Input, Conv2D, Dense, Reshape, Dot, Activation, Multiply

def get_model():
    optimizer = SGD(lr=self.lr, momentum=0.0, decay=0.0, nesterov=False)
    input_layer = Input(shape=(8, 8, 8), name='board_layer')
    inter_layer_1 = Conv2D(1, (1, 1), data_format="channels_first")(input_layer)  # 1,8,8
    inter_layer_2 = Conv2D(1, (1, 1), data_format="channels_first")(input_layer)  # 1,8,8
    flat_1 = Reshape(target_shape=(1, 64))(inter_layer_1)
    flat_2 = Reshape(target_shape=(1, 64))(inter_layer_2)
    output_dot_layer = Dot(axes=1)([flat_1, flat_2])
    output_layer = Reshape(target_shape=(4096,))(output_dot_layer)
    model = Model(inputs=[input_layer], outputs=[output_layer])
    return model

board = chess.variant.AntichessBoard()
print(type(board).uci_variant)
print(board)

model = get_model()
print(model.summary())
