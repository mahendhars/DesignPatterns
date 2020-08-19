package com.composite;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

interface SomeNeurons extends Iterable<Neuron> {
	default void connectTo(SomeNeurons other) {
		if (this == other) return;

	    for (Neuron from : this)
	      for (Neuron to : other)
	      {
	        from.out.add(to);
	        to.in.add(from);
	      }
	}
}

class Neuron implements SomeNeurons {
	public ArrayList<Neuron> in, out;

	@Override
	public Iterator<Neuron> iterator() {
		return Collections.singleton(this).iterator();
	}
	
//	public void connectTo(Neuron other) {
//		out.add(other);
//		other.in.add(this);
//	}
}

class NeuronLayer extends ArrayList<Neuron> implements SomeNeurons {
	
}

public class CompositeDemo2 {
	
	public static void main(String[] args) {
		Neuron neuron1 = new Neuron();
		Neuron neuron2 = new Neuron();
		
		NeuronLayer layer1 = new NeuronLayer();
		NeuronLayer layer2 = new NeuronLayer();
		
		neuron1.connectTo(neuron2);
		neuron1.connectTo(layer1);
		layer1.connectTo(neuron1);
		layer1.connectTo(layer2);
	}
}
