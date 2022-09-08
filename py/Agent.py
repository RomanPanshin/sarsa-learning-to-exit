class Agent(object):
    def __init__(self, gamma=0.5, lr=0.01):
        """
        Args:
            gamma: float
                Temporal discount factor
            network: str
                'linear' or 'conv'
            lr: float
                Learning rate, ideally around 0.1
        """
        self.gamma = gamma
        self.network = network
        self.lr = lr
        self.init_network()
        self.weight_memory = []
        self.long_term_mean = []
        
    def init_network(self):
        self.network =
